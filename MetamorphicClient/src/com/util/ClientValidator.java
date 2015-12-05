/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.util;

import java.io.File;

/**
 *
 * @author Sujith
 */
public class ClientValidator {

    public static boolean isNull(String fine) {
        if (fine.equals("") || fine == null) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean isDigit(String fine) {
        boolean status = false;
        char[] fineArray = fine.toCharArray();
        for (char val : fineArray) {
            if (Character.isDigit(val)) {
                status = true;
                continue;
            } else {
                status = false;
                break;
            }
        }
        return status;
    }

    public static boolean isValidEmailId(String emailId) {
        int atIndex = emailId.indexOf("@");
        int dotIndex = emailId.lastIndexOf(".");
        if (atIndex != -1 && dotIndex != -1) {
            String domain = emailId.substring(atIndex + 1);
            if (domain.indexOf("@") == -1) {
                String dom = emailId.substring(emailId.lastIndexOf(".") + 1);
                if (dom.length() <= 3 && (atIndex + 2) <= dotIndex) {
                    return true;
                } else {
                    return false;
                }
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    public static String validate_Login(String username, String password) {
        if (isNull(username)) {
            return "Username field is required..!";
        } else if (isNull(password)) {
            return "Password field is required..!";
        } else {
            return "";
        }
    }

    public static boolean isValidPhone(String phone) {
        boolean status = false;
        if (isDigit(phone) && phone.length() == 10) {
            status = true;
        }
        return status;
    }

    public static String validateRegistration(String username, String password, String firstName,
            String lastName,String email,String phone, String address1, String address2) {
        if (isNull(username)) {
            return "Username field can't be left blank. Enter value";
        } else if (isNull(password)) {
            return "Password field can't be left blank. Enter value";
        } else if (isNull(firstName)) {
            return "FirstName field can't be left blank. Enter value";
        } else if (isNull(lastName)) {
            return "LastName field can't be left blank. Enter value";
        } else if (isNull(email)) {
            return "EMail Address  can't be left blank. Enter value";
        } else if (!isValidEmailId(email)) {
            return "Invalid email address found..!";
        } else if (isNull(phone)) {
            return "Phone field can't be left blank. Enter value";
        } else if (!isValidPhone(phone)) {
            return "Invalid Phone number found..!";
        } else if (isNull(address1)) {
            return "Address field1 can't be left blank. Enter value";
        } else if (isNull(address2)) {
            return "FirstName field2 can't be left blank. Enter value";
        }
        return "";
    }

    public static String valiadateEncryption(String text, File coverImageFile, String px, String py) {
         if (isNull(text)) {
            return "Text message field can't be left blank. Enter value";
        } else if (coverImageFile == null) {
            return "CoverImage field can't be left blank. Upload CoverImage";
        } else if (isNull(px)) {
            return "pX field can't be left blank. Enter value";
        } else if (!isDigit(px)) {
            return "pX field requires digits only..!";
        } else if (isNull(py)) {
            return "pY field  can't be left blank. Enter value";
         } else if (!isDigit(py)) {
            return "pY field requires digits only..!";
        }
        return "";
    }

    public static String validateDecrypting(File coverImageFile, String px, String py, File cipherImageFile) {
        if (coverImageFile == null) {
            return "CoverImage field can't be left blank. Upload CoverImage";
        } else if (isNull(px)) {
            return "pX field can't be left blank. Enter value";
        } else if (!isDigit(px)) {
            return "pX field requires digits only..!";
        } else if (isNull(py)) {
            return "pY field  can't be left blank. Enter value";
         } else if (!isDigit(py)) {
            return "pY field requires digits only..!";
         } else if (cipherImageFile == null) {
             return "CipherImage field can't be left blank. Upload CipherImage";
        }
        return "";
    }
}
