package net.codejava.CafeManager.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Сущность обеда из базы данных
 */

@Data
@Entity
@Table(name = "lunch")
public class Lunch {
    /**
     * Поле id сущности
     */
    @Id
    @Column(name = "id")
    private Integer id;
    /**
     * Поле названия обеда
     * представляется в виде типа String
     */
    @Column(name = "name")
    private String name;
    /**
     * Поле состава обеда
     * представляется в виде типа String
     */
    @Column(name = "structure")
    private String structure;
    /**
     * Поле описания обеда
     * представляется в виде типа String
     */
    @Column(name = "description")
    private String description;
    /**
     * Поле цены обеда
     * представляется в виде типа Integer
     */
    @Column(name = "price")
    private Integer price;
}