package ru.nuthatch.baseentity.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.util.UUID;

/**
 * Описание комплексного типа: Representative.
 * Представитель, имеющий ФИО, должность
 */
@Data
@MappedSuperclass
public class BaseRepresentative implements Serializable {

    @Id
    @GeneratedValue
    protected UUID uuid;

    /**
     * Фамилия, Имя, Отчество. Обязательный элемент
     */
    @Embedded
    protected FullNameGroup fullNameGroup;

    /**
     * Должность.
     * Обязательный элемент.
     * Минимум 1 символ
     */
    @Column(nullable = false)
    protected String position;

    /**
     * Номер специалиста в реестре Нострой
     */
    protected String nostroyNumber;

    /**
     * Распорядительный документ, подтверждающий полномочия.
     * Обязательный элемент
     */
    protected UUID administrativeDocument;

}
