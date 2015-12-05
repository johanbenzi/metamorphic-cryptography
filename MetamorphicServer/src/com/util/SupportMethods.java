/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.util;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Administrator
 */
public class SupportMethods {

   /* public String loadBookInfo(BookDetailBean detailBean) {
       

        bookBuilder.append("\tBooks Info\n").
                append("Title         :").append(detailBean.getTitle()).append("\n").
                append("Writer        :").append(detailBean.getWriter()).append("\n").
                append("Publisher     :").append(detailBean.getPublisher()).append("\n").
                append("Category      :").append(detailBean.getCategory()).append("\n").
                append("Quantity      :").append(detailBean.getQuantity());

        return bookBuilder.toString();

    }*/

    public static void CloseInstance(Connection con, PreparedStatement statement) {
        try {

            if (statement != null) {
                statement.close();
            }
            if (con != null) {
                con.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(SupportMethods.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void CloseInstance(Connection con, ResultSet resultSet, PreparedStatement statement) {
        try {
            if (resultSet != null) {
                resultSet.close();
            }
            if (statement != null) {
                statement.close();
            }
            if (con != null) {
                con.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(SupportMethods.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
