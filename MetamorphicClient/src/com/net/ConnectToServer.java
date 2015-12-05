/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.net;

import com.util.AppConstants;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

//import com.rdh.transfer.Message;
/**
 *
 * @author fg
 */
public class ConnectToServer implements Runnable {

    //stegno.HomeFrame sted = new HomeFrame();
    Socket clientSocket = null;
    String rqtType = "";
    ObjectInputStream responseStream = null;
    ObjectOutputStream sendingStream = null;

    public ConnectToServer() {
        try {
            clientSocket = new Socket(AppConstants.SERVER_IP, AppConstants.SERVER_PORT);
        } catch (UnknownHostException ex) {
            Logger.getLogger(ConnectToServer.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ConnectToServer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public ConnectToServer(String rqtType) {
        try {
            // this.stegnoClient = stegnoClient;
            this.rqtType = rqtType;
            clientSocket = new Socket(AppConstants.SERVER_IP, AppConstants.SERVER_PORT);
        } catch (UnknownHostException ex) {

            JOptionPane.showMessageDialog(null, "Server is down");

        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Server is down");

        }
    }

    public Object sendRequestToServer(Object request) {
        if (clientSocket != null) {
            try {
                //Send request to server
                sendingStream = new ObjectOutputStream(clientSocket.getOutputStream());
                sendingStream.writeObject(request);
                sendingStream.flush();

                //Accept response
                responseStream = new ObjectInputStream(clientSocket.getInputStream());
                Object response = responseStream.readObject();
                return handleResponse(response);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(ConnectToServer.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(ConnectToServer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return "";
    }

    @Override
    public void run() {
        //  throw new UnsupportedOperationException("Not supported yet.");
    }

    private Object handleResponse(Object response) {
        if (response instanceof String) {
            if (rqtType.equals(AppConstants.TYPE_LOGIN)) {
                return (String) response;
            } else if (rqtType.equals(AppConstants.TYPE_REGISTRATION)) {
                return (String) response;
            } else if (rqtType.equals(AppConstants.STARTING_FILE_TRANSFER)) {
                return (String) response;
            } else if (rqtType.equals(AppConstants.START_FILE_DOWNLOADING)) {
                return (String) response;
            } else if (rqtType.equals(AppConstants.DELETE_INBOX)) {
                return (String) response;
            } else {
                return (String) response;
            }
        } else if (response instanceof ArrayList) {
            if (rqtType.equals(AppConstants.GET_CLIENTS)) {
                return (ArrayList) response;
            }
            if (rqtType.equals(AppConstants.GET_INBOX)) {
                return (ArrayList) response;
            }
        }
        return "";
    }
}
