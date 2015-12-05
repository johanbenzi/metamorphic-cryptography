/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.bean;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author staff-255
 */
public class FileDownloadBean implements Serializable {

     private String username = "";
     private String fileid = "";
     private String filename = "";
     private Object date = null;

    public Object getDate() {
        return date;
    }

    public void setDate(Object date) {
        this.date = date;
    }

    public String getFileid() {
        return fileid;
    }

    public void setFileid(String fileid) {
        this.fileid = fileid;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    

        



}
