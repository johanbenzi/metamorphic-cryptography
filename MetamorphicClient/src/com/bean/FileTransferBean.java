/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bean;

import java.io.File;
import java.io.Serializable;
import javax.swing.ImageIcon;

/**
 *
 * @author Administrator
 */
public class FileTransferBean implements Serializable {

    private String fromAddress = "";
    private String toAddress = "";
    private String cipherFileName = "";
    private String coverFileName = "";
    private String coverFileLocation = "";
    private String cipherFileLocation = "";
    private String date = "";
    private ImageIcon cipherImage = null;
    private File selectedCoverFile = null;
    private File selectedCipherFile = null;
    private int pX = 0;
    private int pY = 0;
    private ImageIcon coverImage = null;
    String email = "";
    String mobile = "";

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public ImageIcon getCipherImage() {
        return cipherImage;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setCipherImage(ImageIcon cipherImage) {
        this.cipherImage = cipherImage;
    }

    public ImageIcon getCoverImage() {
        return coverImage;
    }

    public void setCoverImage(ImageIcon coverImage) {
        this.coverImage = coverImage;
    }

    public File getSelectedCipherFile() {
        return selectedCipherFile;
    }

    public void setSelectedCipherFile(File selectedCipherFile) {
        this.selectedCipherFile = selectedCipherFile;
    }

    public int getpX() {
        return pX;
    }

    public void setpX(int pX) {
        this.pX = pX;
    }

    public int getpY() {
        return pY;
    }

    public void setpY(int pY) {
        this.pY = pY;
    }

    public File getSelectedCoverFile() {
        return selectedCoverFile;
    }

    public void setSelectedCoverFile(File selectedCoverFile) {
        this.selectedCoverFile = selectedCoverFile;
    }

    public String getCipherFileLocation() {
        return cipherFileLocation;
    }

    public void setCipherFileLocation(String cipherFileLocation) {
        this.cipherFileLocation = cipherFileLocation;
    }

    public String getCipherFileName() {
        return cipherFileName;
    }

    public void setCipherFileName(String cipherFileName) {
        this.cipherFileName = cipherFileName;
    }

    public String getCoverFileLocation() {
        return coverFileLocation;
    }

    public void setCoverFileLocation(String coverFileLocation) {
        this.coverFileLocation = coverFileLocation;
    }

    public String getCoverFileName() {
        return coverFileName;
    }

    public void setCoverFileName(String coverFileName) {
        this.coverFileName = coverFileName;
    }

    public String getFromAddress() {
        return fromAddress;
    }

    public void setFromAddress(String fromAddress) {
        this.fromAddress = fromAddress;
    }

    public String getToAddress() {
        return toAddress;
    }

    public void setToAddress(String toAddress) {
        this.toAddress = toAddress;
    }
}
