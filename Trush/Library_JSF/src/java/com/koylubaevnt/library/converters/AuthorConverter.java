/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.koylubaevnt.library.converters;

import com.koylubaevnt.library.controllers.AuthorController;
import com.koylubaevnt.library.db.DataHelper;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

/**
 *
 * @author KojlubaevNT
 */
public class AuthorConverter implements Converter {

    private static AuthorController authorController;
    
    public AuthorConverter() {
        authorController = (AuthorController) FacesContext.getCurrentInstance().getExternalContext().getApplicationMap().get("authorController");
    }
    
    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        return DataHelper.getInstance().getAuthor(Long.valueOf(value));
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        return "";
    }
    
}
