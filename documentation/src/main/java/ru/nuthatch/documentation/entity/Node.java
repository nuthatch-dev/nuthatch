package ru.nuthatch.documentation.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Тип Node: единица в перечне документации - каталог/документ
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "node")
public class Node extends CommonEntity implements Comparable<Node> {
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

    @Override
    public int compareTo(Node o) {
        if (this.document == null && o.document == null) {
            return this.name.compareTo(o.name);
        }
        if (this.document == null) {
            return -1;
        }
        if (o.document == null) {
            return 1;
        }
        return this.document.date.compareTo(o.document.date);
    }
}
