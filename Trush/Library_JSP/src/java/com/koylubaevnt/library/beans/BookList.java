package com.koylubaevnt.library.beans;

import com.koylubaevnt.library.db.Database;
import com.koylubaevnt.library.enums.SearchType;
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
public class BookList {
    
    private final List<Book> bookList = new ArrayList<>();

    public List<Book> getBookList() {
        if(bookList.isEmpty()) {
            getBooks("select select id, name, content, page_count, isbn, genre_id genre, author_id author, publish_year, publisher_id publisher, image from genre order by name");
        }
        return bookList;
    }
    
    public List<Book> getAllBooks() {
        return getBooks("select select id, name, content, page_count, isbn, genre_id genre, author_id author, publish_year, publisher_id publisher, image from genre order by name");
    }
    public List<Book> getBooksByGenre(long genreId) {
        StringBuilder sql = new StringBuilder();
        sql.append(" select b.id, b.name, b.content, b.page_count, b.isbn, g.name genre, a.fio author, b.publish_year, p.name publisher, b.image ");
        sql.append(" from book b ");
        sql.append(" inner join author a on b.author_id = a.id ");
        sql.append(" inner join publisher p on b.publisher_id = p.id ");
        sql.append(" inner join genre g on b.genre_id = g.id ");
        if (genreId != 0) {
            sql.append(" where b.genre_id = ");
            sql.append(genreId);
        }
        sql.append(" order by b.name ");
        return getBooks(sql.toString());
    }
    
    public List<Book> getBooksByLetter(String letter) {
        StringBuilder sql = new StringBuilder();
        sql.append(" select b.id, b.name, b.content, b.page_count, b.isbn, g.name genre, a.fio author, b.publish_year, p.name publisher, b.image ");
        sql.append(" from book b ");
        sql.append(" inner join author a on b.author_id = a.id ");
        sql.append(" inner join publisher p on b.publisher_id = p.id ");
        sql.append(" inner join genre g on b.genre_id = g.id ");
        sql.append(" where lower(substr(b.name, 1, 1)) = '");
        sql.append(letter.toLowerCase().charAt(0));
        sql.append("' ");
        sql.append(" order by b.name");
        return getBooks(sql.toString());
    }
    
    public List<Book> getBooksBySearch(String searchString, SearchType searchType) {
        StringBuilder sql = new StringBuilder();
        sql.append(" select b.id, b.name, b.content, b.page_count, b.isbn, g.name genre, a.fio author, b.publish_year, p.name publisher, b.image ");
        sql.append(" from book b ");
        sql.append(" inner join author a on b.author_id = a.id ");
        sql.append(" inner join publisher p on b.publisher_id = p.id ");
        sql.append(" inner join genre g on b.genre_id = g.id ");
        if(searchType == SearchType.AUTHOR) {
            sql.append(" where lower(a.fio) like '%");
        } else if(searchType == SearchType.TITLE) {
            sql.append(" where lower(b.name) like '%");
        }
        sql.append(searchString.toLowerCase());
        sql.append("%' ");
        sql.append(" order by b.name");
        return getBooks(sql.toString());
    }
    
    private List<Book> getBooks(String sql) {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            connection = Database.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            while(resultSet.next()) {
                bookList.add(new Book(
                    resultSet.getLong("id"),
                        resultSet.getString("name"),
                    null,
                    resultSet.getInt("page_count"),
                    resultSet.getString("isbn"),
                    resultSet.getString("genre"),
                    resultSet.getString("author"),
                    resultSet.getInt("publish_year"),
                    resultSet.getString("publisher"),
                    resultSet.getBytes("image")));
            }
        } catch (NamingException | SQLException ex) {
            Logger.getLogger(BookList.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if(resultSet != null)
                    resultSet.close();
                if(statement != null)
                    statement.close();
                if(connection != null)
                    connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(BookList.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return bookList;
    }
    
    
}
