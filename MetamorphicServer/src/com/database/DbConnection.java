/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.database;

import com.util.Appconstants;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Amrutha
 */
public class DbConnection {

    Connection con = null;

    public Connection getConnection() {
        try {
            if (con == null) {
                con = createConnection();
            }
            return con;
        } catch (ClassNotFoundException ex) {
            return null;
        }
    }

    private Connection createConnection() throws ClassNotFoundException {
        Connection connection = null;
        try {
            Class.forName(Appconstants.DRIVER_URL);
            connection = DriverManager.getConnection(Appconstants.DB_URL, Appconstants.USERNAME, Appconstants.PASSWORD);
        } catch (SQLException ex) {
            Logger.getLogger(DbConnection.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        return connection;

    }
}
