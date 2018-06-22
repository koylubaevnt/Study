/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.koylubaevnt.library.models;

import com.koylubaevnt.library.beans.Pager;
import com.koylubaevnt.library.db.DataHelper;
import com.koylubaevnt.library.entity.Book;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

/**
 *
 * @author KojlubaevNT
 */
public class BookListDataModel extends LazyDataModel<Book> {
    
    private List<Book> bookList;
    private DataHelper dataHelper = DataHelper.getInstance();
    private Pager pager = Pager.getInstance();//FOR JSF
    
    public BookListDataModel() {
    }

    @Override
    public Book getRowData(String rowKey) {
        for(Book book : bookList) {
            if(Objects.equals(book.getId(), Long.valueOf(rowKey))) {
                return book;
            }
        }
        return null;
    }

    @Override
    public Object getRowKey(Book object) {
        return object.getId();
    }

    @Override
    public List<Book> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
        pager.setFrom(first);
        pager.setTo(pageSize);
        dataHelper.populateList();
        
        this.setRowCount(pager.getTotalBooksCount());
        
        return pager.getList();
    }
    
    
}
