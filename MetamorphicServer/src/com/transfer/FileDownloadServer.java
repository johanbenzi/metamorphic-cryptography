/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.transfer;


import com.bean.FileMetaData;
import com.bean.FilePacket;
import com.bean.FileTransferCompleted;
import com.util.Appconstants;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author staff-255
 */
public class FileDownloadServer implements Runnable {

    ServerSocket ssocket = null;
    Socket socket = null;
    ObjectOutputStream oos = null;
    String filePath = "";
    String filename = "";
    byte[] filechunk = null;
    FilePacket filePacket = null;
    FileInputStream fis = null;
    int readcount = 0;

    public FileDownloadServer() {
        try {
            ssocket = new ServerSocket(Appconstants.FILE_DOWNLOAD_PORT);
        } catch (IOException ex) {
            Logger.getLogger(FileDownloadServer.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

   public FileDownloadServer(String filePath, String filename) {
        this();
        this.filePath = filePath;
        this.filename = filename;

    }

    public void run() {

        try {
            socket = ssocket.accept();
            System.out.println("server: connected 4 file file download...");
            oos = new ObjectOutputStream(socket.getOutputStream());
            File selectedfile = new File(filePath);
            FileMetaData fileMetaData = new FileMetaData(filename);
            oos.writeObject(fileMetaData);
            oos.flush();
            oos.reset();
            long length = selectedfile.length();
            int noofpackets = (int) (length / 1024);

            fis = new FileInputStream(selectedfile);
            for (int i = 0; i < noofpackets; i++) {
                filechunk = new byte[1024];
                readcount = fis.read(filechunk);
                filePacket = new FilePacket(filechunk, readcount);
                oos.writeObject(filePacket);
                oos.flush();
                System.out.println("File Packet Length : " + readcount + "-----------");
                System.out.println("File InputStream Available : " + fis.available());
            }

            if (fis.available() > 0) {
                // readcount = fis.available();
                System.out.println(" Sending last file packet");
                filechunk = new byte[1024];
                readcount = fis.read(filechunk);
                filePacket = new FilePacket(filechunk, readcount);
                oos.writeObject(filePacket);
                oos.flush();
                oos.reset();
            }
            System.out.println("File Transfer Completed");
            oos.writeObject(new FileTransferCompleted());
            oos.flush();
            oos.reset();
        } catch (IOException ex) {
            Logger.getLogger(FileDownloadServer.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {

                if (oos != null) {
                    oos.close();
                    System.out.println(": file download server object output stream closed ");
                }
                if (socket != null) {
                    socket.close();
                      System.out.println(": file download server : client socket closed ");
                }
                if (ssocket != null) {
                    ssocket.close();
                        System.out.println(": filedownloadserver  :server socket closed ");
                }
            } catch (IOException ex) {
            }

        }
    }
}
