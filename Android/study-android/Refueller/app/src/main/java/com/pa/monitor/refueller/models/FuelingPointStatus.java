package com.pa.monitor.refueller.models;


/**
 * Created by koylu on 15.09.2017.
 */
public enum FuelingPointStatus {

    /**
     * Идет налив
     */
    FUELLING(1L, "Идет налив"),
    /**
     * Ожидание оплаты
    */
    WAITING_PAYMENT(2L, "Ожидание оплаты"),
    /**
     *  Оплачено
     */
    PAYED(3L, "Оплачено"),
    /**
     * Свободно
     */
    FREE(0L, "Свободно"),
    /**
     * Закрыто
     */
    CLOSE(5L, "Закрыто"),
    /**
     * Неизвестно
     */
    UNKNOWN(-1L, "Неизвестно");

    private Long id;
    private String name;

    FuelingPointStatus(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public static FuelingPointStatus valueOf(Long id) {
        for (FuelingPointStatus item: FuelingPointStatus.values()) {
            if (item.getId().equals(id)) {
                return item;
            }
        }
        return FuelingPointStatus.UNKNOWN;
    }

    @Override
    public String toString() {
        return "FuelingPointStatus{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", tag=" + name() +
                '}';
    }
}
