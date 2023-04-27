package net.codejava.CafeManager.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Сущность дессерта из базы данных
 */

@Data
@Entity
@Table(name = "desserts")
public class Cafe {
    /**
     * Поле id сущности
     */
    @Id
    @Column(name = "id")
    private Integer id;
    /**
     * Поле названия дессерта
     * представляется в виде типа String
     */
    @Column(name = "name")
    private String name;
    /**
     * Поле описания дессерта
     * представляется в виде типа String
     */
    @Column(name = "description")
    private String description;
    /**
     * Поле цены дессерта
     * представляется в виде типа Integer
     */
    @Column(name = "price")
    private Integer price;
}