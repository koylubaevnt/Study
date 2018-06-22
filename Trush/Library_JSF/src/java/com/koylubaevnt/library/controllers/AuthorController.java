/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.koylubaevnt.library.controllers;

import com.koylubaevnt.library.comparators.ListComparator;
import com.koylubaevnt.library.db.DataHelper;
import com.koylubaevnt.library.entity.Author;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.model.SelectItem;

/**
 *
 * @author KojlubaevNT
 */
@ManagedBean(eager = false)
@ApplicationScoped
public class AuthorController implements Serializable, Converter {
    
    private List<SelectItem> selectItems = new ArrayList<>();
    private Map<Long, Author> authorMap;
    private List<Author> authorList;
    
    public AuthorController() {
        authorMap = new HashMap<>();
        authorList = DataHelper.getInstance().getAllAuthors();
        Collections.sort(authorList, ListComparator.getInstance());
        
        for(Author author : authorList) {
            authorMap.put(author.getId(), author);
            selectItems.add(new SelectItem(author, author.getFio()));
        }
    }

    public List<SelectItem> getSelectItems() {
        return selectItems;
    }

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        return authorMap.get(Long.valueOf(value));
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        return ((Author) value).getId().toString();
    }
    
    
    
    
    
}
