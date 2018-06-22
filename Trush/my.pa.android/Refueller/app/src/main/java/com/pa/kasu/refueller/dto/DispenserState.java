package com.pa.kasu.refueller.dto;

/**
 * Состояние ТРК
 */
public enum DispenserState {

    /**
     *  Простаивает
     */
    IDLE(0L, "Оплачено"),
    /**
     * Идет налив
     */
    FUELLING(1L, "Идет налив"),
    /**
     * Ожидание оплаты
     */
    WAITING_PAYING(2L, "Ожидание оплаты"),
    /**
     * Оплачено
     */
    PAYED(2L, "Ожидание оплаты"),
    /**
     * Закрыто
     */
    CLOSE(4L, "Закрыто");

    private Long id;
    private String name;

    DispenserState(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public static DispenserState valueOf(Long id) {
        for (DispenserState item: DispenserState.values()) {
            if (item.getId().equals(id)) {
                return item;
            }
        }
        return DispenserState.CLOSE;
    }

    @Override
    public String toString() {
        return "DispenserState{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", tag=" + name() +
                '}';
    }
}