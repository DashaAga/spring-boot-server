package net.codejava.CafeManager.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Сущность напитка (кофе или чай) из базы данных
 */
@Data
@Entity
@Table(name = "coffe")
public class Coffe {
    /**
     * Поле id сущности
     */
    @Id
    @Column(name = "id")
    private Integer id;
    /**
     * Поле названия напитка
     * представляется в виде типа String
     */
    @Column(name = "name")
    private String name;
    /**
     * Поле описания напитка
     * представляется в виде типа String
     */
    @Column(name = "description")
    private String description;
    /**
     * Поле цены напитка
     * представляется в виде типа Integer
     */
    @Column(name = "price")
    private Integer price;
}