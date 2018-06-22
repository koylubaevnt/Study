/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.koylubaevnt.library.controllers;

import com.koylubaevnt.library.comparators.ListComparator;
import com.koylubaevnt.library.db.DataHelper;
import com.koylubaevnt.library.entity.Genre;
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
 * @author koylu
 */
@ManagedBean(eager = false)
@ApplicationScoped
public class GenreController implements Serializable, Converter {
    
    private List<SelectItem> selectItems = new ArrayList<>();
    private List<Genre> genreList;
    private Map<Long, Genre> genreMap;

    public GenreController() {
        genreMap = new HashMap<>();
        genreList = DataHelper.getInstance().getAllGenres();
        Collections.sort(genreList, ListComparator.getInstance());
        
        for(Genre genre : genreList) {
            genreMap.put(genre.getId(), genre);
            selectItems.add(new SelectItem(genre, genre.getName()));
        }
    }

    public List<SelectItem> getSelectItems() {
        return selectItems;
    }

    public List<Genre> getGenreList() {
        return genreList;
    }

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
       return genreMap.get(Long.valueOf(value));
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        return ((Genre) value).getId().toString();
    }
    
    
}
