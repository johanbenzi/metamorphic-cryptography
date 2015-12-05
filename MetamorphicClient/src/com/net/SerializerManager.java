/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.net;

import com.bean.FileTransferBean;
import com.util.AppConstants;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Amritha
 */
public class SerializerManager {

    public static File serializeData(FileTransferBean ftb) {
        FileOutputStream fos = null;
        File serFile = null;
        ObjectOutputStream oos = null;
        try {
            File tempDir = new File(AppConstants.TEMP_PATH);
            if (!tempDir.exists()) {
                tempDir.mkdirs();
            }
            serFile = new File(tempDir + File.separator + AppConstants.SERIALIZED_FILE);
            fos = new FileOutputStream(serFile);
            oos = new ObjectOutputStream(fos);
            oos.writeObject(ftb);
            oos.flush();
            oos.reset();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(SerializerManager.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(SerializerManager.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                fos.close();
            } catch (IOException ex) {
                Logger.getLogger(SerializerManager.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                oos.close();
            } catch (IOException ex) {
                Logger.getLogger(SerializerManager.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return serFile;
    }
}
