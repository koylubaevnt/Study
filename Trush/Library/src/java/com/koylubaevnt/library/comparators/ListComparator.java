/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.koylubaevnt.library.comparators;

import java.util.Comparator;

/**
 *
 * @author KojlubaevNT
 */
public class ListComparator {
    
    private static ObjectComparator listComparator;
    
    public static Comparator getInstance() {
        if (listComparator == null) {
            listComparator = new ObjectComparator();
        }
        return listComparator;
    }
    
    private static class ObjectComparator implements Comparator {

        @Override
        public int compare(Object o1, Object o2) {
            return o1.toString().compareTo(o2.toString());
        }
        
    }
}
