/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.util;

import com.bean.FileTransferBean;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author Administrator
 */
public class Appvariable {

    public static boolean SERVER_STARTED = false;
    public static String MAIL_NOT_SEND = "Mail not send";
    public static String MAIL_SEND = "Mail send";
    public static boolean CONTROL = false;
    public static long THREAD_RESTART_DELAY = 4000;
    public static HashMap<String,FileTransferBean> PENDING_THREAD = new HashMap<String,FileTransferBean>();
}
