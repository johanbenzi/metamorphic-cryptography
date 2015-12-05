/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.util;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.UnknownHostException;

/**
 *
 * @author SUJITH
 */
public class ValidateInternetConnection {

    static boolean connected = false;

    public static boolean isAvailable() {
        try {
            URL url = new URL("http://www.google.co.in");
            URLConnection connection = url.openConnection();
            if (connection.getContentLength() == -1) {
                connected = false;
            } else {
                connected = true;
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        return connected;
    }

    public static boolean isInternetReachable() {
        try {
            //make a URL to a known source
            URL url = new URL("http://www.google.com");

            //open a connection to that source
            HttpURLConnection urlConnect = (HttpURLConnection) url.openConnection();

            //trying to retrieve data from the source. If there
            //is no connection, this line will fail
            Object objData = urlConnect.getContent();

        } catch (UnknownHostException e) {
            // TODO Auto-generated catch block
            return false;
        } catch (IOException e) {
            // TODO Auto-generated catch block
            return false;
        }
        return true;
    }

    public static void main(String[] args) {
        if (ValidateInternetConnection.isAvailable()) {
            System.out.println("Connection Enabled");
        } else {
            System.out.println("Connection Disabled");
        }
    }
}
