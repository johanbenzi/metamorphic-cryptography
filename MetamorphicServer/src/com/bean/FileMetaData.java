/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.bean;

import java.io.Serializable;

/**
 *
 * @author Students
 */
public class FileMetaData implements Serializable {

    String relativeFilePath = "";
    int chunkSize = 1024;

    public FileMetaData(String relativeFilepath) {
        this.relativeFilePath = relativeFilepath;
    }

    public FileMetaData(String relativeFilepath, int chunkSize) {
        this.chunkSize = chunkSize;
        this.relativeFilePath = relativeFilepath;
    }

    public int getChunkSize() {
        return chunkSize;
    }

    public void setChunkSize(int chunkSize) {
        this.chunkSize = chunkSize;
    }

    public String getRelativeFilePath() {
        return relativeFilePath;
    }

    public void setRelativeFilePath(String relativeFilePath) {
        this.relativeFilePath = relativeFilePath;
    }
}

