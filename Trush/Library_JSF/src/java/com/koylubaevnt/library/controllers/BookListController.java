/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.koylubaevnt.library.controllers;

import com.koylubaevnt.library.beans.Pager;
import com.koylubaevnt.library.db.DataHelper;
import com.koylubaevnt.library.entity.Book;
import com.koylubaevnt.library.enums.SearchType;
import java.io.Serializable;
import java.util.Map;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;

/**
 *
 * @author KojlubaevNT
 */
@ManagedBean(eager = true)
@SessionScoped
public class BookListController implements Serializable{

    private Long selectedAuthorId;      // текущий автор книги из списка при редактирвоании книги
    // критерии поиска
    private char selectedLetter;// выбранная буква алфавита, по умолчанию не выбрана ни одна буква
    private SearchType selectedSearchType = SearchType.TITLE;// хранит выбранный тип поиска, по-умолчанию - по названию
    private long selectedGenreId;// выбранный жанр
    private String currentSearchString;// хранит поисковую строку
    private Pager<Book> pager = new Pager<>();//FOR JSF
    
    private boolean editModeView;// отображение режима редактирования
    
    private transient int row = -1;
    
    public BookListController() {
        fillBooksAll(); //For JSF
    }

    private void submitValues(Character selectedLetter, int selectedPageNumber, long selectedGenreId) {
        this.selectedLetter = selectedLetter;
        this.selectedGenreId = selectedGenreId;
        pager.setSelectedPageNumber(selectedPageNumber);
    }
    
    //<editor-fold defaultstate="collapsed" desc="работа с базой данных">
    private void fillBooksAll() {
       DataHelper.getInstance().getAllBooks(pager);
    }
    
    public void fillBooksByGenre() {
        //imitateLoading();
        cancelEditMode();
        
        row = -1;
        
        Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        
        if (params.get("genre_id") == null) return;// "books";
            
        submitValues(' ', 1, Long.valueOf(params.get("genre_id")));
        DataHelper.getInstance().getBooksByGenre(selectedGenreId, pager);
        
        //return "books";
    }
    
    public void fillBooksByLetter() {
        //imitateLoading();
        cancelEditMode();
        
        row = -1;
        
        Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        submitValues(params.get("letter").charAt(0), 1, -1);
        DataHelper.getInstance().getBooksByLetter(selectedLetter, pager);
    }
    
    public void fillBooksBySearch() {
        //imitateLoading();
        cancelEditMode();
        
        row = -1;
        
        submitValues(' ', 1, -1);

        if(currentSearchString.trim().isEmpty()) {
            fillBooksAll();
        }
        
        switch (selectedSearchType) {
            case AUTHOR :
                DataHelper.getInstance().getBooksByAuthor(currentSearchString, pager);
                break;
            case TITLE:
                DataHelper.getInstance().getBooksByName(currentSearchString, pager);
                break;
        }
    }
    
    public void  updateBooks() {
        row = -1;
        
        DataHelper.getInstance().update();
        
        cancelEditMode();
        
        DataHelper.getInstance().refreshList();
    }
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="режим редактирования">
    public void showEdit() {
        row = -1;
        editModeView = true;
    }
    
    public void cancelEditMode() {
        row = -1;
        editModeView = false;
        for (Book book : pager.getList()) {
            book.setEdit(false);
        }
                
        
    }
    //</editor-fold>
    
    public Character[] getRussianLetters() {
        return new Character[]{'А', 'Б', 'В', 'Г', 'Д', 'Е', 'Ё', 'Ж', 'З', 'И', 'Й', 'К', 'Л', 'М', 'Н', 'О', 'П', 'Р', 'С', 'Т', 'У', 'Ф', 'Х', 'Ц', 'Ч', 'Ш', 'Щ', 'Ъ', 'Ы', 'Ь', 'Э', 'Ю', 'Я'};
    }
    
    public void searchStringChanged(ValueChangeEvent e) {
        currentSearchString = e.getNewValue().toString();
    }
    
    public void searchTypeChanged(ValueChangeEvent e) {
        selectedSearchType = (SearchType) e.getNewValue();
    }
    
    //<editor-fold defaultstate="collapsed" desc="постраничность">
    public void changeBooksCountOnPage(ValueChangeEvent e) {
        row = -1;
        cancelEditMode();
        
        pager.setBooksCountOnPage(Integer.valueOf(e.getNewValue().toString()));
        pager.setSelectedPageNumber(1);
        DataHelper.getInstance().refreshList();
    }
    
    public void selectPage() {
        row = -1;
        
        cancelEditMode();
        Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        pager.setSelectedPageNumber(Integer.valueOf(params.get("page_number")));
        DataHelper.getInstance().refreshList();
    }
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="геттеры и сеттеры">
    public boolean isEditModeView() {
        return editModeView;
    }
    
    public String getSearchString() {
        return currentSearchString;
    }

    public void setSearchString(String currentSearchString) {
        this.currentSearchString = currentSearchString;
    }

    public SearchType getSelectedSearchType() {
        return selectedSearchType;
    }

    public void setSelectedSearchType(SearchType selectedSearchType) {
        this.selectedSearchType = selectedSearchType;
    }

    public long getSelectedGenreId() {
        return selectedGenreId;
    }

    public void setSelectedGenreId(long selectedGenreId) {
        this.selectedGenreId = selectedGenreId;
    }

    public char getSelectedLetter() {
        return selectedLetter;
    }

    public void setSelectedLetter(char selectedLetter) {
        this.selectedLetter = selectedLetter;
    }

    public int getRow() {
        row++;
        return row;
    }
    
    public Pager<Book> getPager() {
        return pager;
    }
    //</editor-fold>
}
