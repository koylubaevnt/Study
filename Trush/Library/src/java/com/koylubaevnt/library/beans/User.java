/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.koylubaevnt.library.beans;

import java.io.Serializable;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author KojlubaevNT
 */
@ManagedBean
@SessionScoped
public class User implements Serializable{

    private String username;
    private String password;

    /**
     * Creates a new instance of User
     */
    public User() {
    }
    
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String login() {
        try {
            
            try {
                Thread.sleep(2000);// имитация загрузки процесса
            } catch (InterruptedException e) {
                Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, e);
            }
            HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
            if(request.getUserPrincipal() == null ) {
                request.login(username, password);
            } else if(!request.getUserPrincipal().getName().equals(username)) {
                request.logout();
                request.login(username, password);
            }
            
            return "books";
        } catch (ServletException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
            FacesContext context = FacesContext.getCurrentInstance();
            ResourceBundle bundle = ResourceBundle.getBundle("com.koylubaevnt.library.nls.messages", FacesContext.getCurrentInstance().getViewRoot().getLocale());
            FacesMessage message = new FacesMessage(bundle.getString("login_password_invalid"));
            message.setSeverity(FacesMessage.SEVERITY_ERROR);
            context.addMessage("login_form", message);
        }
        
        return "index";
    }
    
    public String logout() {
        String result = "/index.xhtml?faces-redirect=true";
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
        
        try {
            request.logout();
        } catch (ServletException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        
        return result;
    }
    
    @Override
    public String toString() {
        return new StringBuilder().append("User [username='").append(username).append("']").toString();
    }
    
    
}
