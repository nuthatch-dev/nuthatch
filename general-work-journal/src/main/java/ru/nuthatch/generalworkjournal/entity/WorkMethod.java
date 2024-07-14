package ru.nuthatch.generalworkjournal.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.util.UUID;

/**
 * Описание комплексного типа WorkMethod
 * Метод выполнения работы
 */
@Data
@Entity
@Table(name = "work_method")
public class WorkMethod implements Serializable {

    @Id
    @GeneratedValue
    protected UUID uuid;

    /**
     * Наименование метода
     * Обязательный элемент
     */
    @Column(name = "work_method_name",
            nullable = false)
    protected String workMethodName;
}
