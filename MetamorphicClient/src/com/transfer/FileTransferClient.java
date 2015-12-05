/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.transfer;

import com.bean.FileMetaData;
import com.bean.FilePacket;
import com.bean.FileTransferCompleted;
import com.gui.EncryptDialog;
import com.util.AppConstants;
import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Administrator
 */
public class FileTransferClient implements Runnable {
    
    private Socket socket = null;
    private ObjectInputStream ois = null;
    private ObjectOutputStream oos = null;
    private FileInputStream fis = null;
    private File selectedFile = null;
    private String date = "";
    private long fileSize = 0;
    private long noOfPackets = 0;
    private byte[] filechunk = null;
    private int currentChunkSize = 0;
    private FilePacket filePacket = null;
    private EncryptDialog parent = null;
    
    public FileTransferClient() {
        try {
            System.out.println("Inside file transfer client constructor");
            socket = new Socket(AppConstants.SERVER_IP, AppConstants.FILE_SERVER_PORT);
            System.out.println("Connection established with server port 6000");
            oos = new ObjectOutputStream(socket.getOutputStream());
        } catch (UnknownHostException ex) {
            Logger.getLogger(FileTransferClient.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(FileTransferClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public FileTransferClient(File selectedFile, String date, EncryptDialog parent) {
        this();
        this.date = date;
        this.selectedFile = selectedFile;
        this.parent = parent;
    }
    
    public void run() {
        try {
            // writing file meta data
            System.out.println("writing file meta data");
            FileMetaData fmd = new FileMetaData(date + selectedFile.getName());
            oos.writeObject(fmd);
            oos.flush();
            oos.reset();
            
            File selFile = new File(selectedFile.getAbsolutePath());
            fileSize = selFile.length();
            noOfPackets = fileSize / 1024;
            fis = new FileInputStream(selFile);
            for (int i = 0; i < noOfPackets; i++) {
                filechunk = new byte[1024];
                currentChunkSize = fis.read(filechunk);
                //System.out.println(new String(filechunk));
                filePacket = new FilePacket(filechunk, currentChunkSize);
                oos.writeObject(filePacket);
                oos.flush();
                System.out.println("File Packet Length : " + currentChunkSize + "-----------");
                // System.out.println(new String(filePacket.getPayLoad()));
                System.out.println("File InputStream Available : " + fis.available());
                
                
            }
            
            oos.reset();
            
            if (fis.available() > 0) {
                currentChunkSize = fis.available();
                
                filechunk = new byte[1024];
                fis.read(filechunk);
                filePacket = new FilePacket(filechunk, currentChunkSize);
                // System.out.println(filePacket.getLength() + new String(filePacket.getPayLoad(),0, currentChunkSize));
                oos.writeObject(filePacket);
                oos.flush();
                oos.reset();
            }
            
            System.out.println("File Transfer Completed");
            
            oos.writeObject(new FileTransferCompleted());
            oos.flush();
            oos.reset();

            //Forward response to interfce
            ois = new ObjectInputStream(socket.getInputStream());
            if (ois != null) {
                try {
                    Object response = ois.readObject();
                    if (response instanceof String) {
                        if (response.toString().equals(AppConstants.FILE_TANSFER_COMPLETED)) {
                            parent.showStatus(AppConstants.FILE_TANSFER_COMPLETED);
                        }
                    }
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(FileTransferClient.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
            
        } catch (IOException ex) {
            Logger.getLogger(FileTransferClient.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (oos != null) {
                try {
                    oos.close();
                    System.out.println("oos closed!");
                } catch (IOException ex) {
                    Logger.getLogger(FileTransferClient.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (socket != null) {
                try {
                    socket.close();
                    System.out.println(" client socket closed!");
                } catch (IOException ex) {
                    Logger.getLogger(FileTransferClient.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
}
