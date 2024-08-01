package ru.nuthatch.generalworkjournal.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import ru.nuthatch.generalworkjournal.common.CommonEntity;

import java.io.Serializable;
import java.util.UUID;

/**
 * Описание комплексного типа: ExecutiveGovernmentAgencyRepresentative
 * Уполномоченное лицо органа (организации), осуществляющего выдачу разрешения
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "executive_government_agency_representative")
public class ExecutiveGovernmentAgencyRepresentative extends CommonEntity implements Serializable {

    /**
     * Представитель
     * Обязательный элемент
     */
    @Column(name = "representative",
            nullable = false)
    protected UUID representative;

    /**
     * Сведения о распорядительном документе, подтверждающем полномочия
     * Обязательный элемент
     */
    @Column(name = "administrative_document",
            nullable = false)
    protected UUID administrativeDocument;
}
