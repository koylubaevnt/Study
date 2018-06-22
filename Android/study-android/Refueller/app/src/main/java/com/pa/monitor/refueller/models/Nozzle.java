package com.pa.monitor.refueller.models;

import java.io.Serializable;

/**
 * Created by koylu on 16.09.2017.
 */
public class Nozzle  implements Serializable {

    private Long id;
    private String name;
    private Good good;

    public Nozzle() {
    }

    public Nozzle(Long id, String name, Good good) {
        this.id = id;
        this.name = name;
        this.good = good;
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

    public Good getGood() {
        return good;
    }

    public void setGood(Good good) {
        this.good = good;
    }

    @Override
    public String toString() {
        return "Nozzle{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", good=" + good +
                '}';
    }

}
