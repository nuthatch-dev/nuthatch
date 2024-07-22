package ru.nuthatch.generalworkjournal.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import ru.nuthatch.generalworkjournal.common.CommonEntity;

import java.io.Serializable;

/**
 * Описание комплексного типа WorkMethod
 * Метод выполнения работы
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "work_method")
public class WorkMethod extends CommonEntity implements Serializable {

    /**
     * Наименование метода
     * Обязательный элемент
     */
    @Column(name = "work_method_name",
            nullable = false)
    protected String workMethodName;
}
