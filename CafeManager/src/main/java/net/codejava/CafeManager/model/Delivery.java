package net.codejava.CafeManager.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Сущность заказа доставки из базы данных
 */

@Data
@Entity
@Table(name = "delivery")
public class Delivery {
    /**
     * Поле id сущности
     */
    @Id
    @Column(name = "id")
    private Integer id;
    /**
     * Поле ФИО заказчика
     * представляется в виде типа String
     */
    @Column(name = "fio")
    private String fio;
    /**
     * Поле состава заказа
     * представляется в виде типа String
     */
    @Column(name = "structure")
    private String structure;
    /**
     * Поле адреса доставки заказа
     * представляется в виде типа String
     */
    @Column(name = "address")
    private String address;
    /**
     * Поле цены доставки
     * представляется в виде типа Integer
     */
    @Column(name = "price")
    private Integer price;
    /**
     * Поле номера заказа у пользователя
     * представляется в виде типа Integer
     */
    @Column(name = "num")
    private Integer num;
}