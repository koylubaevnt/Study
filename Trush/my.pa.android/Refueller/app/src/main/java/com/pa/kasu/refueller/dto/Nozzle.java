package com.pa.kasu.refueller.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Пистолет на ТРК
 */
public class Nozzle  implements Serializable {

    /**
     * Идентификатор пистолета
     */
    @JsonProperty("NozzleId")
    private Long id;
    /**
     * Наименование продукта
     */
    @JsonProperty("ProductName")
    private String productName;
    /**
     * Цена продукта
     */
    @JsonProperty("ProductPrice")
    private BigDecimal productPrice;

    public Nozzle() {
    }

    public Nozzle(Long id, String productName, BigDecimal productPrice) {
        this.id = id;
        this.productName = productName;
        this.productPrice = productPrice;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public BigDecimal getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(BigDecimal productPrice) {
        this.productPrice = productPrice;
    }

    @Override
    public String toString() {
        return "Nozzle{" +
                "id=" + id +
                ", productName='" + productName + '\'' +
                ", productPrice=" + productPrice +
                '}';
    }

}
