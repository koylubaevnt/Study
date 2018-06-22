/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.koylubaevnt.library.controllers;

import com.koylubaevnt.library.comparators.ListComparator;
import com.koylubaevnt.library.db.DataHelper;
import com.koylubaevnt.library.entity.Publisher;
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
public class PublisherController implements Serializable, Converter {
    
    private List<SelectItem> selectItems = new ArrayList<>();
    private List<Publisher> publisherList;
    private Map<Long, Publisher> publisherMap;

    public PublisherController() {
        publisherMap = new HashMap<>();
        publisherList = DataHelper.getInstance().getAllPublishers();
        Collections.sort(publisherList, ListComparator.getInstance());
        
        for(Publisher publisher : publisherList) {
            publisherMap.put(publisher.getId(), publisher);
            selectItems.add(new SelectItem(publisher, publisher.getName()));
        }
    }

    public List<SelectItem> getSelectItems() {
        return selectItems;
    }

    public List<Publisher> getGenreList() {
        return publisherList;
    }

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
       return publisherMap.get(Long.valueOf(value));
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        return ((Publisher) value).getId().toString();
    }
    
    
    
}
