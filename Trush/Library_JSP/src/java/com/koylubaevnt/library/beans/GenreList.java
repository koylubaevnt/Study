/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.koylubaevnt.library.beans;

import com.koylubaevnt.library.db.Database;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;

/**
 *
 * @author KojlubaevNT
 */
public class GenreList {
    
    private final List<Genre> genreList = new ArrayList<>();

    public List<Genre> getGenreList() {
        if(genreList.isEmpty()) {
            getGenres();
        }
        return genreList;
    }

    private List<Genre> getGenres() {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            connection = Database.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery("select * from genre");
            while(resultSet.next()) {
                genreList.add(new Genre(resultSet.getLong("id"), resultSet.getString("name")));
            }
        } catch (NamingException | SQLException ex) {
            Logger.getLogger(GenreList.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if(resultSet != null)
                    resultSet.close();
                if(statement != null)
                    statement.close();
                if(connection != null)
                    connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(GenreList.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return genreList;
    }
    
    
}
