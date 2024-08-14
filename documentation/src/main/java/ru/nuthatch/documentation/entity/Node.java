package ru.nuthatch.documentation.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
/**
 * Тип Node: единица в перечне документации - каталог/документ
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "node")
public class Node extends CommonEntity {
    /*
    Наименование. Устанавливается как имя каталога при отсутствии
    присоединенного CustomDocument. Иначе устанавливается null
     */
    protected String name;

    @OneToOne
    protected CustomDocument document;

    /*
    Родительский элемент (каталог)
     */
    @ManyToOne
    protected Node parentNode;

}
