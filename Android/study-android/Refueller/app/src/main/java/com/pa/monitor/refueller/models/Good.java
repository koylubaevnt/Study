package com.pa.monitor.refueller.models;

import java.io.Serializable;

/**
 * Created by koylu on 16.09.2017.
 */
public class Good implements Serializable {

    private Long id;
    private String name;

    public Good() {
    }

    public Good(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Good{" +
                "id=" + id +
                ", name=" + name +
                '}';
    }

}
