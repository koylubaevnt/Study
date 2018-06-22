/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.koylubaevnt.library.listeners;

import java.util.HashMap;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.http.HttpSession;

/**
 * Web application lifecycle listener.
 *
 * @author KojlubaevNT
 */
public class ContextListener implements ServletContextListener {

    private HashMap<String, HttpSession> sessionHashMap = new HashMap<>();
    
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        sce.getServletContext().setAttribute("sessionHashMap", sessionHashMap);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        sce.getServletContext().removeAttribute("sessionHashMap");
        sessionHashMap = null;
    }
}
