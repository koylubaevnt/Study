/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.koylubaevnt.library.db;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import testjdbc.TestConnection;

/**
 *
 * @author KojlubaevNT
 */
public class Database {
    
    private static Connection connection;
    private static InitialContext initialContext;
    private static DataSource dataSource;
    
    public static Connection getConnection() throws NamingException , SQLException{
       
        if(connection != null) {
            if(connection.isClosed()) {
                connection = dataSource.getConnection();
            }
        } else {
            initialContext = new InitialContext();
            dataSource = (DataSource) initialContext.lookup("jdbc/Library");
            connection = dataSource.getConnection();
        }
        return connection;
    }
    
    
}
