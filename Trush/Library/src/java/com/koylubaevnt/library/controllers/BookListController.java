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
import com.koylubaevnt.library.models.BookListDataModel;
import java.io.Serializable;
import java.util.Map;
import java.util.ResourceBundle;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import org.primefaces.component.datatable.DataTable;
import org.primefaces.context.RequestContext;
import org.primefaces.model.LazyDataModel;

/**
 *
 * @author KojlubaevNT
 */
@ManagedBean(eager = true)
@SessionScoped
public class BookListController implements Serializable{

    private DataTable dataTable;
    private Book selectedBook;
    
    private DataHelper dataHelper = DataHelper.getInstance();
    private LazyDataModel<Book> bookListModel;
    
    private Long selectedAuthorId;      // текущий автор книги из списка при редактирвоании книги
    // критерии поиска
    private char selectedLetter;// выбранная буква алфавита, по умолчанию не выбрана ни одна буква
    private SearchType selectedSearchType = SearchType.TITLE;// хранит выбранный тип поиска, по-умолчанию - по названию
    private long selectedGenreId;// выбранный жанр
    private String currentSearchString;// хранит поисковую строку
    private Pager pager = Pager.getInstance();//FOR JSF
    
    private boolean editModeView;// отображение режима редактирования
    
    public BookListController() {
        bookListModel = new BookListDataModel();
    }

    private void submitValues(Character selectedLetter, long selectedGenreId) {
        this.selectedLetter = selectedLetter;
        this.selectedGenreId = selectedGenreId;
        dataTable.setFirst(0);
    }
    
    //<editor-fold defaultstate="collapsed" desc="работа с базой данных">
    private void fillBooksAll() {
       dataHelper.getAllBooks();
    }
    
    public void fillBooksByGenre() {
        cancelEditMode();
        Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        submitValues(' ', Long.valueOf(params.get("genre_id")));
        dataHelper.getBooksByGenre(selectedGenreId);
    }
    
    public void fillBooksByLetter() {
        cancelEditMode();
        Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        submitValues(params.get("letter").charAt(0), -1);
        dataHelper.getBooksByLetter(selectedLetter);
    }
    
    public void fillBooksBySearch() {
        cancelEditMode();
        submitValues(' ', -1);
        if(currentSearchString.trim().isEmpty()) {
            fillBooksAll();
        }
        switch (selectedSearchType) {
            case AUTHOR :
                dataHelper.getBooksByAuthor(currentSearchString);
                break;
            case TITLE:
                dataHelper.getBooksByName(currentSearchString);
                break;
        }
    }
    
    public void  updateBooks() {
        dataHelper.updateBook(selectedBook);
        cancelEditMode();
        dataHelper.populateList();
        
        RequestContext.getCurrentInstance().execute("PF('dlgEditBook').hide()");
        
        ResourceBundle bundle = ResourceBundle.getBundle("com.koylubaevnt.library.messages", FacesContext.getCurrentInstance().getViewRoot().getLocale());
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(bundle.getString("updated")));
        dataTable.setFirst(calcSelectedPage());
    }
    
    public void deleteBook() {
        dataHelper.deleteBook(selectedBook);
        dataHelper.populateList();
        
        ResourceBundle bundle = ResourceBundle.getBundle("com.koylubaevnt.library.messages", FacesContext.getCurrentInstance().getViewRoot().getLocale());
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(bundle.getString("deleted")));
        dataTable.setFirst(calcSelectedPage());
    }
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="режим редактирования">
    public void switchEditMode() {
        editModeView = true;
        RequestContext.getCurrentInstance().execute("PF('dlgEditBook').show()");
    }
    
    public void cancelEditMode() {
        editModeView = false;
        RequestContext.getCurrentInstance().execute("PF('dlgEditBook').hide()");        
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
    
    private int calcSelectedPage() {
        int page = dataTable.getPage();
        
        int leftBound = pager.getTo() * (page - 1);
        int rightBound = pager.getTo() * page;
        
        boolean goPrevPage = pager.getTotalBooksCount() > leftBound & pager.getTotalBooksCount() <= rightBound;
        if(goPrevPage) {
            page--;
        }
        return (page > 0) ? page * pager.getTo() : 0;
    }

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

    public Long getSelectedAuthorId() {
        return selectedAuthorId;
    }

    public void setSelectedAuthorId(Long selectedAuthorId) {
        this.selectedAuthorId = selectedAuthorId;
    }
    
    public Pager getPager() {
        return pager;
    }
    
    public LazyDataModel<Book> getBookListModel() {
        return bookListModel;
    }
    
    public Book getSelectedBook() {
        return selectedBook;
    }

    public void setSelectedBook(Book selectedBook) {
        this.selectedBook = selectedBook;
    }

    
    public DataTable getDataTable() {
        return dataTable;
    }

    public void setDataTable(DataTable dataTable) {
        this.dataTable = dataTable;
    }

    //</editor-fold>

    
}
