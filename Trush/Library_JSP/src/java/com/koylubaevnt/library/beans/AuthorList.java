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
import testjdbc.TestConnection;

/**
 *
 * @author KojlubaevNT
 */
public class AuthorList {
    
    private final List<Author> authorList = new ArrayList<>();

    public List<Author> getAuthorList() {
        if(authorList.isEmpty()) {
            getAuthors();
        }
        return authorList;
    }

    private List<Author> getAuthors() {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            connection = Database.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery("select * from author order by fio");
            while(resultSet.next()) {
                authorList.add(new Author(resultSet.getLong("id"),resultSet.getString("fio")));
            }
        } catch (NamingException | SQLException ex) {
            Logger.getLogger(TestConnection.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if(resultSet != null)
                    resultSet.close();
                if(statement != null)
                    statement.close();
                if(connection != null)
                    connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(AuthorList.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return authorList;
    }
    
    
}
