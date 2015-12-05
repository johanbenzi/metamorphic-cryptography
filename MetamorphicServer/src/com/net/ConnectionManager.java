/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.net;

import com.bean.FileDownloadBean;
import com.bean.FileTransferBean;
import com.bean.LoginBean;
import com.bean.RegistrationBean;
import com.database.DbManager;
import com.transfer.FileDownloadServer;
import com.util.Appconstants;
import com.util.Appvariable;
import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author S
 */
public class ConnectionManager implements Runnable {

    private ServerSocket socket = null;
    private Socket serverSideClientSocket = null;
    private ObjectInputStream clientReader = null;
    private ObjectOutputStream clientWriter = null;

    public ConnectionManager() {
        try {
            socket = new ServerSocket(Appconstants.SERVER_PORT);
            Appvariable.SERVER_STARTED = true;
        } catch (IOException ex) {
            Appvariable.SERVER_STARTED = false;
            Logger.getLogger(ConnectionManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void run() {

        while (Appvariable.SERVER_STARTED) {
            try {
                Object response = null;
                serverSideClientSocket = socket.accept();
                clientReader = new ObjectInputStream(serverSideClientSocket.getInputStream());
                if (clientReader != null) {
                    Object readObject = clientReader.readObject();
                    response = handleClientRequest(readObject);

                }
                if (response != null) {
                    ObjectOutputStream responseStream = new ObjectOutputStream(serverSideClientSocket.getOutputStream());
                    responseStream.writeObject(response);
                    responseStream.flush();
                }
            } catch (IOException ex) {
                Logger.getLogger(ConnectionManager.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(ConnectionManager.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private Object handleClientRequest(Object readObject) {
        if (readObject instanceof LoginBean) {
            return new DbManager().checkLogin((LoginBean) readObject);
        } else if (readObject instanceof RegistrationBean) {
            return new DbManager().addUserInfo((RegistrationBean) readObject);
        } else if (readObject instanceof FileTransferBean) {
            return new DbManager().addInboxInfo((FileTransferBean) readObject);
        } else if (readObject instanceof FileDownloadBean) {
            return startFileDownloading((FileDownloadBean) readObject);

        } else if (readObject instanceof String) {
            String requet = (String) readObject;
            if (requet.equals(Appconstants.GET_CLIENTS)) {
                return new DbManager().getClients();
            } else {

                String[] values = requet.split(",");
                if (values[0].equals(Appconstants.GET_INBOX)) {
                    return new DbManager().getInbox(values[1]);
                } else if (values[0].equals(Appconstants.DELETE_INBOX)) {
                    return new DbManager().removeInbox(values[1]);
                }
            }
        }
        return "";
    }

    private String startFileDownloading(FileDownloadBean fdb) {
        System.out.println(" File download started...");
//        String fileid = fdb.getFileid();
        final String filename = fdb.getFilename().trim();

        File cipherFile = new File(Appconstants.WORKING_DIR + Appconstants.CIPHER_REPO_PATH + fdb.getUsername() + File.separator + fdb.getDate() + " CipherImage.png");
        final String cipherPath = cipherFile.getAbsolutePath();
        System.out.println(" File path " + cipherPath);
        File file = new File(cipherPath);
        if (file.getParentFile().exists()) {
            System.out.println(" parent  exists");
        }

        if (file.exists()) {
            System.out.println(" file exists");
            new Thread(new Runnable() {

                public void run() {
                    System.out.println(" in anmous thread");
                    FileDownloadServer downloadServer = new FileDownloadServer(cipherPath, filename);
                    new Thread(downloadServer).start();
                }
            }).start();


            return Appconstants.START_FILE_DOWNLOADING;
        } else {
            System.out.println(" File not exists ");
            return "File downloading failed..!";

        }

    }
}
