/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.transfer;

import com.bean.AcknowledgeBean;
import com.bean.FileMetaData;
import com.bean.FilePacket;
import com.bean.FileTransferCompleted;
import com.net.DeserializerManager;
import com.util.Appconstants;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Administrator
 */
public class FileTransferServer implements Runnable {

    private String username = "";
    private ServerSocket socket = null;
    private Socket clientSocket = null;
    private ObjectInputStream fileReaderStream = null;
    private FileOutputStream fileOutputStream = null;
    private boolean isFileTransferCompleted = false;
    private FileMetaData metaData = null;
    private FilePacket packet = null;
    private ObjectOutputStream oos = null;
    private  File serFile = null;

    public FileTransferServer() {
        try {
            socket = new ServerSocket(Appconstants.FILE_SERVER_PORT);
        } catch (IOException ex) {
            Logger.getLogger(FileTransferServer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public FileTransferServer(String username) {
        this();
        this.username = username;
    }

    @Override
    public void run() {

        AcknowledgeBean ackBean = new AcknowledgeBean();
        try {
            clientSocket = socket.accept();
            System.out.println("server connected 4 file transfer-accepted");
            fileReaderStream = new ObjectInputStream(clientSocket.getInputStream());

            while (!isFileTransferCompleted) {
                try {
                    /*
                     * Reading From Stream
                     */
                    Object object = fileReaderStream.readObject();

                    if (object != null) {
                        if (object instanceof FileMetaData) {
                            if (fileOutputStream != null) {
                                fileOutputStream.flush();
                                fileOutputStream.close();
                            }
                            metaData = (FileMetaData) object;
                            String relativeFilePath = metaData.getRelativeFilePath();
                            String filename = relativeFilePath;
                             serFile = new File(System.getProperty("user.dir")
                                    + Appconstants.TEMP_REPO_PATH + filename);
                            if (!new File(serFile.getParent()).exists()) {
                                new File(serFile.getParent()).mkdirs();
                            }

                            fileOutputStream = new FileOutputStream(serFile);

                        } else if (object instanceof FilePacket) {
                            packet = (FilePacket) object;
                            fileOutputStream.write(packet.getPayLoad(), 0, packet.getLength());
                            fileOutputStream.flush();

                        } else if (object instanceof FileTransferCompleted) {
                            System.out.println(" File Transfer Completed");
                            if (fileOutputStream != null) {
                                fileOutputStream.flush();
                                fileOutputStream.close();


                            }
                            isFileTransferCompleted = true;

                            ackBean.setContext("Filetransfer");
                            ackBean.setMessage("");
                            ackBean.setStatus("true");

                            //clientHandler.writeDataToClient(ackBean);
                            System.out.println(Appconstants.FILE_TANSFER_COMPLETED);

                            //Send response
                            if (isFileTransferCompleted) {
                                oos = new ObjectOutputStream(clientSocket.getOutputStream());
                                oos.writeObject(Appconstants.FILE_TANSFER_COMPLETED);
                                oos.flush();
                                oos.reset();
                                
                                DeserializerManager.deserializeData(serFile);
                            }

                        }

                    } else {
                        System.out.println("");

                    }


                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(FileTransferServer.class.getName()).log(Level.SEVERE, null, ex);

                    ackBean.setContext("Filetransfer");
                    ackBean.setMessage("ClassNotFoundEx");
                    ackBean.setStatus("false");
                }
            }

        } catch (IOException ex) {
            Logger.getLogger(FileTransferServer.class.getName()).log(Level.SEVERE, null, ex);

            ackBean.setContext("Filetransfer");
            ackBean.setMessage("IOEx");
            ackBean.setStatus("false");
        } finally {
            try {
                if (fileOutputStream != null) {
                    fileOutputStream.close();
                    System.out.println("server : fosclosed!");
                }
                if (clientSocket != null) {
                    clientSocket.close();
                    System.out.println("server : client socket closed ");
                }
                if (clientSocket != null) {
                    socket.close();
                    System.out.println("server socket closed!");
                }
            } catch (IOException ex) {
                Logger.getLogger(FileTransferServer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }



    }
}
