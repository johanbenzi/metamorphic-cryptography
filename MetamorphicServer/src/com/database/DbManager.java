/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.database;

import com.bean.FileTransferBean;
import com.bean.LoginBean;
import com.bean.RegistrationBean;
import com.transfer.FileTransferServer;
import com.util.Appconstants;
import com.util.SupportMethods;
import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Administrator
 */
public class DbManager {

    Connection con = null;

    public Object checkLogin(LoginBean loginBean) {
        PreparedStatement statement = null;
        ResultSet recordSet = null;
        String response = (String) Appconstants.Login_Failed;
        try {
            String query = "SELECT user_type FROM login_info WHERE username = binary ? "
                    + " AND password = binary ?";
            con = new DbConnection().getConnection();
            statement = con.prepareStatement(query);
            statement.setString(1, loginBean.getUsername());
            statement.setString(2, loginBean.getPassword());
            recordSet = statement.executeQuery();
            if (recordSet.next()) {
                response = (String) Appconstants.Login_Sucess;
            }
        } catch (SQLException ex) {
            response = (String) Appconstants.Login_Failed;
            Logger.getLogger(DbManager.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            SupportMethods.CloseInstance(con, recordSet, statement);
        }
        return response;
    }

    public String checkAdminLogin(LoginBean loginBean) {
        PreparedStatement statement = null;
        ResultSet recordSet = null;
        String response = "";
        try {
            String query = "SELECT user_type FROM login_info WHERE username = binary ? "
                    + " AND password = binary ?";
            con = new DbConnection().getConnection();
            statement = con.prepareStatement(query);
            statement.setString(1, loginBean.getUsername());
            statement.setString(2, loginBean.getPassword());
            recordSet = statement.executeQuery();
            if (recordSet.next()) {
                response = recordSet.getString("user_type");
            }
        } catch (SQLException ex) {
            response = (String) Appconstants.Login_Failed;
            Logger.getLogger(DbManager.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            SupportMethods.CloseInstance(con, recordSet, statement);
        }
        return response;
    }

    public Object addUserInfo(RegistrationBean bean) {
        PreparedStatement statement = null;
        ResultSet recSet = null;
        String response = (String) Appconstants.REGISTRATION_FAILED;
        try {
            String query = "select * from login_info where username = ? ";
            String loginQuery = "INSERT INTO login_info(username,password,user_type) VALUES(?,?,?)";
            String regQuery = "INSERT INTO userdetails(username,name,last_name,email_id,phone, address) VALUES(?,?,?,?,?,?)";

            con = new DbConnection().getConnection();
            statement = con.prepareStatement(query);
            statement.setString(1, bean.getUserName());
            recSet = statement.executeQuery();
            if (!recSet.next()) {
                con.setAutoCommit(false);
                statement = con.prepareStatement(loginQuery);
                statement.setString(1, bean.getUserName());
                statement.setString(2, bean.getPassword());
                statement.setString(3, Appconstants.TYPE_CLIENT);
                int executeUpdate = statement.executeUpdate();
                if (executeUpdate > 0) {
                    PreparedStatement regStmt = con.prepareStatement(regQuery);
                    regStmt.setString(1, bean.getUserName());
                    regStmt.setString(2, bean.getFirstName());
                    regStmt.setString(3, bean.getLastName());
                    regStmt.setString(4, bean.getEmail());
                    regStmt.setString(5, bean.getMobile());
                    regStmt.setString(6, bean.getAddress());
                    int regStatus = regStmt.executeUpdate();
                    if (regStatus > 0) {
                        con.commit();
                        con.setAutoCommit(true);
                        response = Appconstants.REGISTERED_SUCCESSFULLY;
                    }
                }
            }
        } catch (SQLException ex) {
            try {
                response = (String) Appconstants.Login_Failed;
                con.rollback();
                con.setAutoCommit(true);
                Logger.getLogger(DbManager.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex1) {
                Logger.getLogger(DbManager.class.getName()).log(Level.SEVERE, null, ex1);
            }
        } finally {
            SupportMethods.CloseInstance(con, statement);
            SupportMethods.CloseInstance(con, recSet, statement);
        }
        return response;
    }

    public Object getClients() {
        PreparedStatement statement = null;
        ResultSet recordSet = null;
        ArrayList<Object> responseList = new ArrayList<Object>();
        try {
            String query = "SELECT username FROM login_info WHERE user_type = ?";
            con = new DbConnection().getConnection();
            statement = con.prepareStatement(query);
            statement.setString(1, Appconstants.TYPE_CLIENT);
            recordSet = statement.executeQuery();
            while (recordSet.next()) {
                responseList.add(recordSet.getString("username"));

            }
        } catch (SQLException ex) {
            Logger.getLogger(DbManager.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            SupportMethods.CloseInstance(con, recordSet, statement);
        }
        return responseList;
    }

    public void updateMailStatus(FileTransferBean ftb) {
        PreparedStatement statement = null;
        try {
            String query = "UPDATE inbox_info SET mail_status = ? WHERE cover_ImageName = ?";// AND toAddress = ?";
            con = new DbConnection().getConnection();
            statement = con.prepareStatement(query);
            statement.setString(1, Appconstants.MAIL_SEND);
            statement.setString(2, ftb.getCoverFileName());
            // statement.setString(3, ftb.getToAddress());
            int executeUpdate = statement.executeUpdate();
            if (executeUpdate > 0) {
                System.out.println("Mail status updated...!");
            }
        } catch (SQLException ex) {
            Logger.getLogger(DbManager.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            SupportMethods.CloseInstance(con, statement);
        }
    }

    public RegistrationBean getClientInfo(String toAddress, String cipherName) {
        PreparedStatement statement = null;
        ResultSet recordSet = null;
        RegistrationBean bean = new RegistrationBean();
        try {
            String query = "SELECT DISTINCT userdetails.email_id, userdetails.phone, inbox_info.cipher_location "
                    + " FROM userdetails "
                    + " JOIN inbox_info WHERE inbox_info.cipher_fileName = ? "
                    + " AND userdetails.username = ?";
            con = new DbConnection().getConnection();
            statement = con.prepareStatement(query);
            statement.setString(1, cipherName);
            statement.setString(2, toAddress);
            recordSet = statement.executeQuery();
            if (recordSet.next()) {
                bean.setUserName(toAddress);
                bean.setEmail(recordSet.getString("email_id"));
                bean.setMobile(recordSet.getString("phone"));
                bean.setCipherFileLocation(recordSet.getString("cipher_location"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(DbManager.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            SupportMethods.CloseInstance(con, recordSet, statement);
        }
        return bean;
    }

    public Object getInbox(String toAddress) {
        PreparedStatement statement = null;
        ResultSet recordSet = null;
        ArrayList<Object> responseList = new ArrayList<Object>();
        try {
            String query = "SELECT senderAddress, toAddress, cover_ImageName, cipher_fileName, date FROM inbox_info WHERE toAddress = ?";
            con = new DbConnection().getConnection();
            statement = con.prepareStatement(query);
            statement.setString(1, toAddress);
            recordSet = statement.executeQuery();
            while (recordSet.next()) {
                FileTransferBean bean = new FileTransferBean();
                bean.setCipherFileName(recordSet.getString("cipher_fileName"));
                bean.setCoverFileName(recordSet.getString("cover_ImageName"));
                bean.setFromAddress(recordSet.getString("senderAddress"));
                bean.setToAddress(recordSet.getString("toAddress"));
                bean.setDate(recordSet.getString("date"));
                responseList.add(bean);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DbManager.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            SupportMethods.CloseInstance(con, recordSet, statement);
        }
        return responseList;
    }

    public Object addInboxInfo(FileTransferBean ftb) {
        PreparedStatement statement = null;
        String response = "";
        //final String filePath = workingdir + Appconstants.FILE_REPO_PATH
        //       + fdb.getUsername() + File.separator + fdb.getDate() + " " + fdb.getFilename();
        try {
            String query = "INSERT INTO inbox_info(senderAddress, toAddress, cipher_fileName, cipher_location,"
                    + " cover_ImageName, cover_location,px,py, date, mail_status) "
                    + " VALUES(?,?,?,?,?,?,?,?,?,?)";
            con = new DbConnection().getConnection();
            File cipherDir = new File(Appconstants.WORKING_DIR + Appconstants.CIPHER_REPO_PATH + ftb.getToAddress());
            if (!cipherDir.exists()) {
                cipherDir.mkdirs();
            }
            File cipherFile = new File(cipherDir + File.separator + ftb.getDate() + " " + Appconstants.CIPHER_IMAGE_NAME);


            File coverDir = new File(Appconstants.WORKING_DIR + Appconstants.COVER_REPO_PATH + ftb.getToAddress());
            if (!coverDir.exists()) {
                coverDir.mkdirs();
            }
            File coverFile = new File(coverDir + File.separator + ftb.getDate() + " " + ftb.getCoverFileName());

            statement = con.prepareStatement(query);
            statement.setString(1, ftb.getFromAddress());
            statement.setString(2, ftb.getToAddress());
            statement.setString(3, ftb.getDate() + " " + Appconstants.CIPHER_IMAGE_NAME);
            statement.setString(4, cipherFile.getAbsolutePath());
            statement.setString(5, ftb.getCoverFileName());
            statement.setString(6, coverFile.getAbsolutePath());
            statement.setInt(7, ftb.getpX());
            statement.setInt(8, ftb.getpY());
            statement.setString(9, ftb.getDate());
            statement.setString(10, Appconstants.MAIL_NOT_SEND);
            int executeUpdate = statement.executeUpdate();
            if (executeUpdate > 0) {
                FileTransferServer server = new FileTransferServer(ftb.getToAddress());
                new Thread(server).start();
                response = Appconstants.STARTING_FILE_TRANSFER;
            }
        } catch (SQLException ex) {
            Logger.getLogger(DbManager.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            SupportMethods.CloseInstance(con, statement);
        }
        return response;
    }

    public ArrayList<FileTransferBean> getPendingInfo() {
        PreparedStatement statement = null;
        ResultSet recordSet = null;
        ArrayList<FileTransferBean> pendingList = new ArrayList<FileTransferBean>();
        try {
            String query = "SELECT inbox_info.senderAddress, inbox_info.toAddress, inbox_info.cover_ImageName, "
                    + " inbox_info.cover_location ,inbox_info.date,inbox_info.px, inbox_info.py, "
                    + " userdetails.email_id, userdetails.phone "
                    + " FROM inbox_info "
                    + " JOIN userdetails ON inbox_info.toAddress = userdetails.username "
                    + " WHERE mail_status = ?";
            con = new DbConnection().getConnection();
            statement = con.prepareStatement(query);
            statement.setString(1, Appconstants.MAIL_NOT_SEND);
            recordSet = statement.executeQuery();
            while (recordSet.next()) {
                FileTransferBean bean = new FileTransferBean();

                bean.setCoverFileName(recordSet.getString("cover_ImageName"));
                bean.setCoverFileLocation(recordSet.getString("cover_location"));
                bean.setFromAddress(recordSet.getString("senderAddress"));
                bean.setToAddress(recordSet.getString("toAddress"));
                bean.setDate(recordSet.getString("date"));
                bean.setEmail(recordSet.getString("email_id"));
                bean.setMobile(recordSet.getString("phone"));
                bean.setpX(recordSet.getInt("px"));
                bean.setpY(recordSet.getInt("py"));

                pendingList.add(bean);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DbManager.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            SupportMethods.CloseInstance(con, recordSet, statement);
        }
        return pendingList;
    }

    public String removeInbox(String date) {
        PreparedStatement statement = null;
        String response = "";
        try {
            String query = "DELETE FROM inbox_info  WHERE date = ?";// AND toAddress = ?";
            con = new DbConnection().getConnection();
            statement = con.prepareStatement(query);
            statement.setString(1, date);
            int executeUpdate = statement.executeUpdate();
            if (executeUpdate > 0) {
                System.out.println("Inbox deleted successfully...");
                response = Appconstants.DELETION_COMPLETED;
            }
        } catch (SQLException ex) {
            Logger.getLogger(DbManager.class.getName()).log(Level.SEVERE, null, ex);
            return "";
        } finally {
            SupportMethods.CloseInstance(con, statement);
        }
        return response;
    }
//27-03-2014 11-29-27
    public boolean checkMailstatus(String date) {
        PreparedStatement statement = null;
        ResultSet recordSet = null;
        boolean status = false;
        try {
            String query = "SELECT mail_status FROM inbox_info WHERE date = ?";
            con = new DbConnection().getConnection();
            statement = con.prepareStatement(query);
            statement.setString(1, date);
            recordSet = statement.executeQuery();
            if (recordSet.next()) {
                if (recordSet.getString("mail_status").equals(Appconstants.MAIL_NOT_SEND)) {
                    status = true;
                }

            }
        } catch (SQLException ex) {
            Logger.getLogger(DbManager.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            SupportMethods.CloseInstance(con, recordSet, statement);
        }
        return status;
    }
}
