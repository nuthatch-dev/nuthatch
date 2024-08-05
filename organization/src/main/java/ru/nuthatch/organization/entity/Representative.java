package ru.nuthatch.organization.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.UUID;

/**
 * Описание комплексного типа: Representative.
 * Представитель, имеющий ФИО, должность
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "representative")
public class Representative extends CommonEntity {

    /**
     * Фамилия, Имя, Отчество. Обязательный элемент
     */
    @Embedded
    protected FullNameGroup fullNameGroup;

    /**
     * Наименование юр. лица.
     */
    @ManyToOne
    protected LegalEntity legalEntity;

    /**
     * Наименование ИП
     */
    @ManyToOne
    protected IndividualEntrepreneur individualEntrepreneur;

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
    // TODO: CustomDocument service
    protected UUID administrativeDocument;

}
