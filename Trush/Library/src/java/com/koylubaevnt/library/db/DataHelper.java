/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.koylubaevnt.library.db;

import com.koylubaevnt.library.beans.Pager;
import com.koylubaevnt.library.entity.Author;
import com.koylubaevnt.library.entity.Book;
import com.koylubaevnt.library.entity.Genre;
import com.koylubaevnt.library.entity.HibernateUtil;
import com.koylubaevnt.library.entity.Publisher;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Property;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;

/**
 *
 * @author KojlubaevNT
 */
public class DataHelper {
    
    private static DataHelper dataHelper;
    
    private SessionFactory sessionFactory = null;
    private DetachedCriteria bookListCriteria;
    private DetachedCriteria booksCountCriteria;
    private ProjectionList bookProjectionList;
    private Pager pager = Pager.getInstance();
    
    private DataHelper() {
        sessionFactory = HibernateUtil.getSessionFactory();
        
        bookProjectionList = Projections.projectionList();
        bookProjectionList.add(Projections.property("id"), "id");
        bookProjectionList.add(Projections.property("name"), "name");
        bookProjectionList.add(Projections.property("image"), "image");
        bookProjectionList.add(Projections.property("genre"), "genre");
        bookProjectionList.add(Projections.property("pageCount"), "pageCount");
        bookProjectionList.add(Projections.property("isbn"), "isbn");
        bookProjectionList.add(Projections.property("publisher"), "publisher");
        bookProjectionList.add(Projections.property("author"), "author");
        bookProjectionList.add(Projections.property("publishYear"), "publishYear");
        bookProjectionList.add(Projections.property("descr"), "descr");
        
        prepareCriterias();
        runCountCriteria();
        
    }
    
    public static DataHelper getInstance() {
        if (dataHelper == null) {
            dataHelper = new DataHelper();
        }
        return dataHelper;
    }
    
    private Session getSession() {
        return sessionFactory.getCurrentSession();
    }
    
    public List<Genre> getAllGenres() {
        return getSession().createCriteria(Genre.class).list();
    }
    
    public List<Author> getAllAuthors() {
        return getSession().createCriteria(Author.class).list();
    }
    
    public List<Publisher> getAllPublishers() {
        return getSession().createCriteria(Publisher.class).list();
    }
    
    public Author getAuthor(long id) {
        return (Author) getSession().get(Author.class, id);
    }
    
    public void getAllBooks() {
        
        prepareCriterias();
        populateList();
    }
    
    public void getBooksByGenre(Long genreId) {
        
        Criterion criterion = Restrictions.eq("genre.id", genreId);
        
        prepareCriterias(criterion);
        populateList();
    }
    
    public void getBooksByLetter(Character letter) {
        
        Criterion criterion = Restrictions.ilike("b.name", letter.toString(), MatchMode.START);
        
        prepareCriterias(criterion);
        populateList();
    }
    
    public void getBooksByAuthor(String authorName) {
        
        Criterion criterion = Restrictions.ilike("author.fio", authorName, MatchMode.ANYWHERE);
        
        prepareCriterias(criterion);
        populateList();
    }
    
    public void getBooksByName(String bookName) {
        
        Criterion criterion = Restrictions.ilike("b.name", bookName, MatchMode.ANYWHERE);
        
        prepareCriterias(criterion);
        populateList();
    }
    
    public byte[] getContent(Long id) {
        Criteria criteria = getSession().createCriteria(Book.class);
        criteria.setProjection(Property.forName("content"));
        criteria.add(Restrictions.eq("id", id));
        return (byte[]) criteria.uniqueResult();
    }
    
    public void updateBook(Book book) {
        
        Query query = getSession().createQuery(
                "update Book " + 
                " set   name = :name, " +
                "       pageCount = :pageCount," + 
                "       isbn = :isbn, " +
                "       genre = :genre, " +
                "       author = :author, " +
                "       publishYear = :publishYear, " +
                "       publisher = :publisher, " +
                "       descr = :descr " + 
                " where id = :id ");
        
        query.setParameter("name", book.getName());
        query.setParameter("pageCount", book.getPageCount());
        query.setParameter("isbn", book.getIsbn());
        query.setParameter("genre", book.getGenre());
        query.setParameter("author", book.getAuthor());
        query.setParameter("publishYear", book.getPublishYear());
        query.setParameter("publisher", book.getPublisher());
        query.setParameter("descr", book.getDescr());
        query.setParameter("id", book.getId());
        
        query.executeUpdate();
    }
    
    public void deleteBook(Book book) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.getTransaction();
        session.delete(book);
        transaction.commit();
        session.flush();
        session.close();
    }
    /*
    public void update() {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.getTransaction();
        transaction.begin();
        for(Object object : pager.getList()) {
            Book book = (Book) object;
            if (book.isEdit()) {
                session.update(book);
            }
        }
        transaction.commit();
        session.flush();
        session.close();
    }
    */
    public void populateList() {
        runCountCriteria();
        runBookListCriteria();
    }
    
    private void runBookListCriteria() {
        Criteria criteria = bookListCriteria.getExecutableCriteria(getSession());
        criteria.addOrder(Order.asc("b.name")).setProjection(bookProjectionList).setResultTransformer(Transformers.aliasToBean(Book.class));
        criteria.setFirstResult(pager.getFrom()).setMaxResults(pager.getTo());
        pager.setList(criteria.list());
    }
    
    private void runCountCriteria() {
        Criteria criteria = booksCountCriteria.getExecutableCriteria(getSession());
        Object o = criteria.setProjection(Projections.rowCount()).uniqueResult();
        Integer total = 0;
        if(o != null) {
            total = Integer.valueOf(o.toString());
        }
        pager.setTotalBooksCount(total);
    }
    
    private void prepareCriterias(Criterion criterion) {
       bookListCriteria = DetachedCriteria.forClass(Book.class, "b");
       createAliases(bookListCriteria);
       bookListCriteria.add(criterion);
       
       booksCountCriteria = DetachedCriteria.forClass(Book.class, "b");
       createAliases(booksCountCriteria);
       booksCountCriteria.add(criterion);
       
    }
    
    private void prepareCriterias() {
       bookListCriteria = DetachedCriteria.forClass(Book.class, "b");
       createAliases(bookListCriteria);
       
       booksCountCriteria = DetachedCriteria.forClass(Book.class, "b");
       createAliases(booksCountCriteria);
       
    }
    
    private void createAliases(DetachedCriteria criteria) {
        criteria.createAlias("b.author", "author");
        criteria.createAlias("b.genre", "genre");
        criteria.createAlias("b.publisher", "publisher");
    }
    
}
