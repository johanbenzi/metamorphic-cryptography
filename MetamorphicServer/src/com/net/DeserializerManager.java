/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.net;

import com.alerts.AlertMail;
import com.alerts.SmsAlert;
import com.bean.FileTransferBean;
import com.bean.RegistrationBean;
import com.database.DbManager;
import com.util.Appconstants;
import com.util.ValidateInternetConnection;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author Amritha
 */
public class DeserializerManager {
    
    private static FileTransferBean ftb = null;
    
    public static void deserializeData(File serFile) {
        try {
            
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(serFile));
            Object readObject = ois.readObject();
            if (readObject instanceof FileTransferBean) {
                ftb = (FileTransferBean) readObject;
                if (ftb != null) {
                    File cipherDir = new File(Appconstants.WORKING_DIR + Appconstants.CIPHER_REPO_PATH + ftb.getToAddress());
                    if (!cipherDir.exists()) {
                        cipherDir.mkdirs();
                    }
                    File cipherFile = new File(cipherDir + File.separator + ftb.getDate() + " " + Appconstants.CIPHER_IMAGE_NAME);
                    Image cipherImage = ftb.getCipherImage().getImage();
                    BufferedImage cipherBuf = new BufferedImage(cipherImage.getWidth(null), cipherImage.getHeight(null), BufferedImage.TYPE_INT_RGB);
                    cipherBuf.createGraphics().drawImage(cipherImage, null, null);
                    ImageIO.write(cipherBuf, "png", cipherFile);
                    
                    
                    File coverDir = new File(Appconstants.WORKING_DIR + Appconstants.COVER_REPO_PATH + ftb.getToAddress());
                    if (!coverDir.exists()) {
                        coverDir.mkdirs();
                    }
                    File coverFile = new File(coverDir + File.separator + ftb.getDate() + " " + ftb.getCoverFileName());
                    Image coverImage = ftb.getCoverImage().getImage();
                    BufferedImage coverBuf = new BufferedImage(coverImage.getWidth(null), coverImage.getHeight(null), BufferedImage.TYPE_INT_RGB);
                    coverBuf.createGraphics().drawImage(coverImage, null, null);
                    ImageIO.write(coverBuf, "png", coverFile);
                    
                    if (ValidateInternetConnection.isInternetReachable()) {
                        RegistrationBean bean = new DbManager().getClientInfo(ftb.getToAddress(), ftb.getDate() + " " + Appconstants.CIPHER_IMAGE_NAME);
                        new DbManager().updateMailStatus(ftb);
                        bean.setDate(ftb.getDate());
                        SmsAlert.sendSms(bean.getMobile(), getMessage(bean, ftb));
                        Thread.sleep(1000);
                        AlertMail alertMail = new AlertMail(bean, coverFile);
                        new Thread(alertMail).start();
                    } else {
                        System.out.println("Internet connection is not available");
                    }
                }
            }
        } catch (InterruptedException ex) {
            Logger.getLogger(DeserializerManager.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DeserializerManager.class.getName()).log(Level.SEVERE, null, ex);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(DeserializerManager.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(DeserializerManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private static String getMessage(RegistrationBean bean, FileTransferBean ftb) {
        StringBuilder sb = new StringBuilder();
        sb.append("Metamorphic Cryptography\n");
        sb.append("Hello ,");
        sb.append(bean.getUserName());
        sb.append("\n");
        sb.append("Message From :");
        sb.append(ftb.getFromAddress());
        sb.append("\n");
        sb.append("PX :");
        sb.append(ftb.getpX());
        sb.append("PY :");
        sb.append(ftb.getpY());
        return sb.toString();
    }
}
