/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.transfer;

import com.bean.FileMetaData;
import com.bean.FilePacket;
import com.bean.FileTransferCompleted;
import com.gui.InboxDialog;
import com.util.AppConstants;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author staff-255
 */
public class FileDownloadClient implements Runnable {

    Socket socket = null;
    ObjectInputStream ois = null;
    String selectedpath = null;
    boolean isFileTransferCompleted = false;
    Object readObject = null;
    FileMetaData fileMetaData = null;
    FileOutputStream fileOutputStream = null;
    FilePacket packet = null;
    InboxDialog parent = null;

    public FileDownloadClient() {
        try {
            // socket = new Socket("localhost", 7000);
            socket = new Socket(AppConstants.SERVER_IP, AppConstants.FILE_DOWNLOAD_PORT);


        } catch (UnknownHostException ex) {
            Logger.getLogger(FileDownloadClient.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(FileDownloadClient.class.getName()).log(Level.SEVERE, null, ex);
        }


    }

    public FileDownloadClient(String selectedpath, InboxDialog parent) {
        this();
        this.selectedpath = selectedpath;
        this.parent = parent;

    }

    public void run() {
        try {
            ois = new ObjectInputStream(socket.getInputStream());

            while (!isFileTransferCompleted) {
                try {
                    readObject = ois.readObject();
                    if (readObject != null) {

                        if (readObject instanceof FileMetaData) {
                            if (fileOutputStream != null) {
                                fileOutputStream.flush();
                                fileOutputStream.close();
                            }

                            fileMetaData = (FileMetaData) readObject;
                            String fileName = fileMetaData.getRelativeFilePath();
                            System.out.println("filename " + fileName);
                            selectedpath = selectedpath + File.separator + fileName;
                            System.out.println("download path" + selectedpath);
                            File downloadfile = new File(selectedpath);
                            if (downloadfile.getParentFile().exists()) {
                                System.out.println("parent file exists" + downloadfile.getParentFile().getPath());
                            }
                            if (!downloadfile.exists()) {
                                System.out.println("file doesnt exists");
                            }
                            fileOutputStream = new FileOutputStream(downloadfile);
                        } else if (readObject instanceof FilePacket) {

                            packet = (FilePacket) readObject;
                            System.out.println("file packet received length " + packet.getLength());
                            fileOutputStream.write(packet.getPayLoad(), 0, packet.getLength());
                            System.out.println("file wrote");
                            fileOutputStream.flush();

                        } else if (readObject instanceof FileTransferCompleted) {
                            System.out.println(" File Transfer Completed");
                            if (fileOutputStream != null) {
                                fileOutputStream.flush();
                                fileOutputStream.close();
                            }
                            isFileTransferCompleted = true;
                            parent.notificationStatus();
                        }
                    }
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(FileDownloadClient.class.getName()).log(Level.SEVERE, null, ex);
                }
            }



        } catch (IOException ex) {
            Logger.getLogger(FileDownloadClient.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (fileOutputStream != null) {
                    fileOutputStream.close();
                }

                if (socket != null) {
                    socket.close();
                    System.out.println("client : download client socket closed ");
                }


            } catch (IOException ex) {
                Logger.getLogger(FileDownloadClient.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

    }
}
