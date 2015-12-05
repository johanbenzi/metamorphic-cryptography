package com.alerts;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

public class SmsAlert {

    public static String retval = "";
    public static String username = "johanbenzi";
    public static String password = "johanpass115";
    public static String sendername = "WEBSMS";
    public static String routetype = "2";

    public static String SMSSender(String username, String password, String mobile, String message, String sendername, String routetype) {
        String rsp = "";

        try {
            // Construct The Post Data
            String data = URLEncoder.encode("username", "UTF-8") + "=" + URLEncoder.encode(username, "UTF-8");
            data += "&" + URLEncoder.encode("password", "UTF-8") + "=" + URLEncoder.encode(password, "UTF-8");
            data += "&" + URLEncoder.encode("mobile", "UTF-8") + "=" + URLEncoder.encode(mobile, "UTF-8");
            data += "&" + URLEncoder.encode("message", "UTF-8") + "=" + URLEncoder.encode(message, "UTF-8");
            data += "&" + URLEncoder.encode("sendername", "UTF-8") + "=" + URLEncoder.encode(sendername, "UTF-8");
            data += "&" + URLEncoder.encode("routetype", "UTF-8") + "=" + URLEncoder.encode(routetype, "UTF-8");

            //Push the HTTP Request
            URL url = new URL("http://clients.smshorizon.in/sms_api/sendsms.php");
            URLConnection conn = url.openConnection();
            conn.setDoOutput(true);

            OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
            wr.write(data);
            wr.flush();

            //Read The Response
            BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = rd.readLine()) != null) {
                // Process line...
                retval += line;
            }
            wr.close();
            rd.close();

            System.out.println(retval);
            rsp = retval;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return rsp;
    }

    public static void sendSms(String phone, String message) {
        String response = SMSSender(username,password, phone, message,sendername,routetype);
        System.out.println(response);
        System.out.println("Sms send to " + phone);
    }

    public static void main(String[] args) {
        //9633009956 Adrija
        //8086461927 Thasneem
        //9633272117 Sadiq
        String response = SMSSender( "johanbenzi","johanpass115", "9447566940", "Metamorphic Cryptography", "WEBSMS", "2");
        System.out.println(response);
    }
}