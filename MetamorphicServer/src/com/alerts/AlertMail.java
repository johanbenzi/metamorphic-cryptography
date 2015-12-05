/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alerts;

import com.bean.FileTransferBean;
import java.io.File;
import java.util.Properties;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

/**
 *
 * @author SUJITH
 */
public class AlertMail implements Runnable {

    private File coverImage = null;
    private FileTransferBean bean = null;

    public AlertMail(FileTransferBean bean, File coverImage) {
        this.bean = bean;
        this.coverImage = coverImage;
    }

    @Override
    public void run() {
        Properties properties = System.getProperties();
        properties.put("mail.smtps.host", "smtp.gmail.com");
        properties.put("mail.smtps.auth", "true");
        properties.put("mail.smtps.quitwait", "false");
        Session session = Session.getInstance(properties);
        try {
            Transport tr = session.getTransport("smtps");
            tr.connect("smtp.gmail.com", "javavfs13@gmail.com", "netbeans");
            session.setDebug(true);
            System.out.println("Account Connected");
            MimeMessage msg = new MimeMessage(session);

            msg.setFrom(new InternetAddress("javavfs13@gmail.com"));
            msg.addRecipients(Message.RecipientType.TO, bean.getEmail());
            //msg.addRecipients(Message.RecipientType.CC, "javavfs15@gmail.com");
            msg.setSubject("Metamorphic Cryptography");
            //msg.setText("hai hello how are you msg");
            //for body message
            BodyPart bp = new MimeBodyPart();
            bp.setText(getMessage(bean));
            //for attachment

            BodyPart bodyPart = new MimeBodyPart();
            DataSource dataSource =
                    new FileDataSource(coverImage.getAbsolutePath());
            bodyPart.setFileName("CoverImage.png");
            bodyPart.setDataHandler(new DataHandler(dataSource));



            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(bp);
            multipart.addBodyPart(bodyPart);
            msg.setContent(multipart);
            msg.saveChanges();
            System.out.println("Message sending");
            tr.sendMessage(msg, msg.getAllRecipients());
            tr.close();
            System.out.println("Message send !!");
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static void main(String[] args) {
    }

    private static String getMessage(FileTransferBean bean) {
        StringBuilder sb = new StringBuilder();
        sb.append("Metamorphic Cryptography\n");
        sb.append("Hello ,");
        sb.append(bean.getToAddress());
        sb.append("\n");
        sb.append("Message From :");
        sb.append(bean.getFromAddress());
        sb.append("\n");
        sb.append("Date :");
        sb.append(bean.getDate());
        return sb.toString();
    }
}
