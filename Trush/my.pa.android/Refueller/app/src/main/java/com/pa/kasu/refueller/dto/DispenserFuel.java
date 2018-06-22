package com.pa.kasu.refueller.dto;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Доза на пистолет
 */
public class DispenserFuel implements Serializable {

    /**
     * Идентификтаор пистолета
     */
    private long nozzleId;
    /**
     * Доза задана деньгами
     */
    private boolean isFuelInMoney;
    /**
     * Количество в литрах
     */
    private BigDecimal quantity;
    /**
     * Количество в деньгах
     */
    private BigDecimal amount;

    public DispenserFuel(long nozzleId, boolean isFuelInMoney, BigDecimal quantity, BigDecimal amount) {
        this.nozzleId = nozzleId;
        this.isFuelInMoney = isFuelInMoney;
        this.quantity = quantity;
        this.amount = amount;
    }

    public long getNozzleId() {
        return nozzleId;
    }

    public void setNozzleId(long nozzleId) {
        this.nozzleId = nozzleId;
    }

    public boolean isFuelInMoney() {
        return isFuelInMoney;
    }

    public void setFuelInMoney(boolean fuelInMoney) {
        isFuelInMoney = fuelInMoney;
    }

    public BigDecimal getQuantity() {
        return quantity;
    }

    public void setQuantity(BigDecimal quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "DispenserFuel{" +
                "nozzleId=" + nozzleId +
                ", isFuelInMoney=" + isFuelInMoney +
                ", quantity=" + quantity +
                ", amount=" + amount +
                '}';
    }
}
