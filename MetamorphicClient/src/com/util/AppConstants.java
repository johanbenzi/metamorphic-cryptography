/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.util;

import java.io.File;

/**
 *
 * @author Administrator
 */
public interface AppConstants {

    public static int SERVER_PORT = 5000;
    public static int FILE_SERVER_PORT = 6000;
    public static int FILE_DOWNLOAD_PORT = 7000;
    public static String SERVER_IP = "localhost";
    public static String TYPE_LOGIN = "LOGIN";
    public static String TYPE_REGISTRATION = "REGISTER";
    public static String Login_Sucess = "Login Sucess";
    public static String Login_Failed = "Login Failed";
    public static String REGISTERED_SUCCESSFULLY = "Registered successfully.";
    public static String REGISTRATION_FAILED = "Registration Failed.";
    public static String GET_CLIENTS = "GET CLIENTS";
    public static String FILE_TRANSFER = "FILE TRANSFER";
    public static String STARTING_FILE_TRANSFER = "Starting File transfer...!";
    public static String FILE_TANSFER_COMPLETED = "File transfer successful";
    public static String DATE_FORMAT = "dd-MM-yyy hh-mm-ss";
    public static boolean SENDING_STATUS = false;
    public static String GET_INBOX = "GET INBOX";
    public static String START_FILE_DOWNLOADING = "Start file downloading";
    public static String FILE_DOWNLOAD = "File Download";
    public static String FILE_DOWNLOAD_COMPLETED = "File downloaded successfully!";
    public static String TEMP_PATH =  System.getProperty("user.dir") + File.separator + "TempFolder";
    public static String SERIALIZED_FILE = "MetaFile.ser";
    public static String DELETE_INBOX = "Delete Inbox";
    public static String DELETION_COMPLETED= "Deletion completed";
    
}
