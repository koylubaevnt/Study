/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.koylubaevnt.library.beans;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author KojlubaevNT
 */
public class Pager<T> {
    private int selectedPageNumber = 1;// выбранный номер страницы в постраничной навигации
    private int booksCountOnPage = 5;// кол-во отображаемых книг на 1 странице
    private int totalBooksCount;// общее кол-во книг (не на текущей странице, а всего), нажно для постраничности
    
    private List<T> list;
    private List<Integer> pageNumbers = new ArrayList<>();
    
    //<editor-fold defaultstate="collapsed" desc="геттеры и сеттеры">
    public int getFrom() {
        return selectedPageNumber * booksCountOnPage - booksCountOnPage;
    }

    public int getTo() {
        return booksCountOnPage;
    }
    
    public List<T> getList() {
        return list;
    }
    
    public void setList(List<T> list) {
        this.list = list;
    }

    public void setTotalBooksCount(int totalBooksCount) {
        this.totalBooksCount = totalBooksCount;
    }

    public int getTotalBooksCount() {
        return totalBooksCount;
    }

    public int getSelectedPageNumber() {
        return selectedPageNumber;
    }

    public void setSelectedPageNumber(int selectedPageNumber) {
        this.selectedPageNumber = selectedPageNumber;
    }

    public int getBooksCountOnPage() {
        return booksCountOnPage;
    }

    public void setBooksCountOnPage(int booksCountOnPage) {
        this.booksCountOnPage = booksCountOnPage;
    }
    //</editor-fold>

    public List<Integer> getPageNumbers() {
        int pageCount = 0;
        if (totalBooksCount % booksCountOnPage == 0) {
            pageCount = booksCountOnPage > 0 ? totalBooksCount / booksCountOnPage : 0;
        } else {
            pageCount = booksCountOnPage > 0 ? totalBooksCount / booksCountOnPage + 1 : 0;
        }
        
        pageNumbers.clear();
        for(int i = 1; i <= pageCount; i++) {
            pageNumbers.add(i);
        }
        
        return pageNumbers;
    }
    
}
