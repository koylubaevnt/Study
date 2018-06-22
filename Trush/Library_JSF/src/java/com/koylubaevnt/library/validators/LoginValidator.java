/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.koylubaevnt.library.validators;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

/**
 *
 * @author KojlubaevNT
 */
@FacesValidator("com.koylubaevnt.library.validators.LoginValidator")
public class LoginValidator implements Validator{

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        ResourceBundle bundle = ResourceBundle.getBundle("com.koylubaevnt.library.nls.messages", FacesContext.getCurrentInstance().getViewRoot().getLocale());
        
        try {
            String newValue = value.toString();
        
            if(!Character.isLetter(newValue.charAt(0))) {
                throw new IllegalArgumentException(bundle.getString("first_leter_error"));
            }

            if(getTestArray().contains(newValue)) {
                throw new IllegalArgumentException(bundle.getString("used_name_error"));
            }
            
            if(newValue.length() < 5) {
                throw new IllegalArgumentException(bundle.getString("login_length_error"));
            }

            
        } catch (IllegalArgumentException ex) {
            FacesMessage message = new FacesMessage(ex.getMessage());
            message.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ValidatorException(message);
        }
        
        
        
    }
    
    private List<String> getTestArray() {
        List<String> list = new ArrayList<>();
        list.add("username");
        list.add("login");
        return list;
    }
}
