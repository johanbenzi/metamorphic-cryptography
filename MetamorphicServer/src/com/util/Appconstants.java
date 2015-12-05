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
public interface Appconstants {

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
    public static String DRIVER_URL = "com.mysql.jdbc.Driver";
    public static String DB_URL = "jdbc:mysql://localhost:3306/metamorphic_db";
    public static String USERNAME = "root";
    public static String PASSWORD = "";
    public static String TYPE_CLIENT = "CLIENT";
    public static String GET_CLIENTS = "GET CLIENTS";
    public static String FILE_TRANSFER = "FILE TRANSFER";
    public static String STARTING_FILE_TRANSFER = "Starting File transfer...!";
    public static String FILE_TANSFER_COMPLETED = "File transfer successful";
//    public static String DATE_FORMAT = "dd-MM-yyyy";
    public static String DATE_FORMAT = "dd-MM-yyy hh-mm-ss";
    public static String GET_INBOX = "GET INBOX";
    public static String CIPHER_IMAGE_NAME = "CipherImage.png";
    public static String START_FILE_DOWNLOADING = "Start file downloading";
    public static String WORKING_DIR = System.getProperty("user.dir");
    public static String CIPHER_REPO_PATH = File.separator + "CipherRepo" + File.separator;
    public static String COVER_REPO_PATH = File.separator + "CoverRepo" + File.separator;
    public static String TEMP_REPO_PATH = File.separator + "SerRepo" + File.separator;
    public static String MAIL_SEND = "MAIL SEND";
    public static String MAIL_NOT_SEND = "Mail not send";
    public static String DELETE_INBOX = "Delete Inbox";
    public static String DELETION_COMPLETED= "Deletion completed";
    public static String TYPE_ADMIN = "admin";
}
