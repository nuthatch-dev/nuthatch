package ru.nuthatch.organization.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import ru.nuthatch.baseentity.entity.BaseRepresentative;

import java.io.Serializable;
import java.util.UUID;

/**
 * Описание комплексного типа: Representative.
 * Представитель, имеющий ФИО, должность
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "representative")
public class Representative extends BaseRepresentative implements BaseEntity {

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

}
