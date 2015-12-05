/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.bean;

import java.io.Serializable;

/**
 *
 * @author staff-255
 */
public class AcknowledgeBean implements Serializable {
    private String status  =  "" ;
    private String message  =  "" ;
    private String context  =  "" ;

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }



}
