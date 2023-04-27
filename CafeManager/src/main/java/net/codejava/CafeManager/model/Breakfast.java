package net.codejava.CafeManager.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Сущность завтрака из базы данных
 */

@Data
@Entity
@Table(name = "breakfast")
public class Breakfast {
    /**
     * Поле id сущности
     */
    @Id
    @Column(name = "id")
    private Integer id;
    /**
     * Поле названия завтрака
     * представляется в виде типа String
     */
    @Column(name = "name")
    private String name;
    /**
     * Поле состава завтрака
     * представляется в виде типа String
     */
    @Column(name = "structure")
    private String structure;
    /**
     * Поле описания завтрака
     * представляется в виде типа String
     */
    @Column(name = "description")
    private String description;
    /**
     * Поле цены завтрака
     * представляется в виде типа Integer
     */
    @Column(name = "price")
    private Integer price;
}