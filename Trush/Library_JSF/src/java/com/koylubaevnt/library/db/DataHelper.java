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
    
    private SessionFactory sessionFactory = null;
    private static DataHelper dataHelper;
    private DetachedCriteria bookListCriteria;
    private DetachedCriteria booksCountCriteria;
    private Pager currentPager;
    private ProjectionList bookProjectionList;
    
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
    
    public void getAllBooks(Pager pager) {
        currentPager = pager;
        
        createBooksCountCriteria();
        runCountCriteria();
        
        createBookListCriteria();
        runBookListCriteria();
    }
    
    public void getBooksByGenre(Long genreId, Pager pager) {
        currentPager = pager;
        
        Criterion criterion = Restrictions.eq("genre.id", genreId);
        
        createBooksCountCriteria(criterion);
        runCountCriteria();
        
        createBookListCriteria(criterion);
        runBookListCriteria();
    }
    
    public void getBooksByLetter(Character letter, Pager pager) {
        currentPager = pager;
        
        Criterion criterion = Restrictions.ilike("b.name", letter.toString(), MatchMode.START);
        
        createBooksCountCriteria(criterion);
        runCountCriteria();
        
        createBookListCriteria(criterion);
        runBookListCriteria();
    }
    
    public void getBooksByAuthor(String authorName, Pager pager) {
        currentPager = pager;
        
        Criterion criterion = Restrictions.ilike("author.fio", authorName, MatchMode.ANYWHERE);
        
        createBooksCountCriteria(criterion);
        runCountCriteria();
        
        createBookListCriteria(criterion);
        runBookListCriteria();
    }
    
    public void getBooksByName(String bookName, Pager pager) {
        currentPager = pager;
        
        Criterion criterion = Restrictions.ilike("b.name", bookName, MatchMode.ANYWHERE);
        
        createBooksCountCriteria(criterion);
        runCountCriteria();
        
        createBookListCriteria(criterion);
        runBookListCriteria();
    }
    
    public byte[] getContent(Long id) {
        Criteria criteria = getSession().createCriteria(Book.class);
        criteria.setProjection(Property.forName("content"));
        criteria.add(Restrictions.eq("id", id));
        return (byte[]) criteria.uniqueResult();
    }
    
    public void update() {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.getTransaction();
        transaction.begin();
        for(Object object : currentPager.getList()) {
            Book book = (Book) object;
            if (book.isEdit()) {
                session.update(book);
            }
        }
        transaction.commit();
        session.flush();
        session.close();
    }
    
    public void refreshList() {
        runCountCriteria();
        runBookListCriteria();
    }
    
    private void createBooksCountCriteria(Criterion criterion) {
       booksCountCriteria = DetachedCriteria.forClass(Book.class, "b");
       booksCountCriteria.add(criterion);
       createCountAliases();
    }
    
    private void createBooksCountCriteria() {
       booksCountCriteria = DetachedCriteria.forClass(Book.class, "b");
       createCountAliases();
    }

    private void createBookListCriteria(Criterion criterion) {
       bookListCriteria = DetachedCriteria.forClass(Book.class, "b");
       bookListCriteria.add(criterion);
       createAliases();
    }
    
    private void createBookListCriteria() {
       bookListCriteria = DetachedCriteria.forClass(Book.class, "b");
       createAliases();
    }
    
    private void runCountCriteria() {
        Criteria criteria = booksCountCriteria.getExecutableCriteria(getSession());
        Integer total = Long.valueOf(criteria.setProjection(Projections.rowCount()).uniqueResult().toString()).intValue();
        currentPager.setTotalBooksCount(total);
        
    }

    private void runBookListCriteria() {
        Criteria criteria = bookListCriteria.addOrder(Order.asc("b.name")).getExecutableCriteria(getSession());
        criteria.setProjection(bookProjectionList).setResultTransformer(Transformers.aliasToBean(Book.class));
        List<Book> list = criteria.setFirstResult(currentPager.getFrom()).setMaxResults(currentPager.getTo()).list();
        currentPager.setList(list);
        
    }
    
    private void createAliases() {
        bookListCriteria.createAlias("b.author", "author");
        bookListCriteria.createAlias("b.genre", "genre");
        bookListCriteria.createAlias("b.publisher", "publisher");
    }
    
    private void createCountAliases() {
        booksCountCriteria.createAlias("b.author", "author");
        booksCountCriteria.createAlias("b.genre", "genre");
        booksCountCriteria.createAlias("b.publisher", "publisher");
    }
    /*
    private void createAliases() {
        bookListCriteria.createAlias("b.author", "author");
        bookListCriteria.createAlias("b.genre", "genre");
        bookListCriteria.createAlias("b.publisher", "publisher");
    }
    */
}
