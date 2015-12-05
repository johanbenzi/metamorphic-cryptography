/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alerts;

import com.bean.FileTransferBean;
import com.database.DbManager;
import com.util.Appvariable;
import com.util.ValidateInternetConnection;
import java.io.File;
import java.util.ArrayList;

/**
 *
 * @author Administrator
 */
public class AlertThread implements Runnable {

    ArrayList<FileTransferBean> pendingList = null;
    String date = "";
    boolean listStatus = true;

    public AlertThread() {
    }

    @Override
    public void run() {
        System.out.println("Date :" + date);
        while (Appvariable.CONTROL) {
            if (ValidateInternetConnection.isInternetReachable()) {
                System.out.println("Internet connection available");
                ArrayList<FileTransferBean> pendingInfo = new DbManager().getPendingInfo();

                for (FileTransferBean bean : pendingInfo) {

                    boolean status = new DbManager().checkMailstatus(bean.getDate());
                    if (status) {
                        SmsAlert.sendSms(bean.getMobile(), getMessage(bean));
                        AlertMail alertMail = new AlertMail(bean, new File(bean.getCoverFileLocation()));
                        
                        new Thread(alertMail).start();
                        new DbManager().updateMailStatus(bean);
                    }

                }



             //   Appvariable.PENDING_THREAD.remove(date);

            } else {
                System.out.println("Internet connection is not available");
            }
        }

//            Iterator it = Appvariable.PENDING_THREAD.entrySet().iterator();
//            while (it.hasNext()) {
//                Map.Entry entry = (Map.Entry) it.next();
//                //String key = (String) entry.getKey();
//                FileTransferBean bean = (FileTransferBean) entry.getValue();
//                if (ValidateInternetConnection.isInternetReachable()) {
//
//
//                    new DbManager().updateMailStatus(bean);
//                    SmsAlert.sendSms(bean.getMobile(), getMessage(bean));
//                    AlertMail alertMail = new AlertMail(bean.getEmail(), new File(bean.getCoverFileLocation()));
//                    new Thread(alertMail).start();
//
//                    Appvariable.PENDING_THREAD.remove(date);
//                } else {
//                    System.out.println("Internet connection is not available");
//                }
//
//
//            }



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
        sb.append("PX :");
        sb.append(bean.getpX());
        sb.append("PY :");
        sb.append(bean.getpY());
        return sb.toString();
    }
}
