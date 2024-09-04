package ru.nuthatch.generalworkjournal.entity.representative;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import ru.nuthatch.baseentity.entity.BaseRepresentative;

/**
 * Описание комплексного типа: Representative.
 * Представитель, имеющий ФИО, должность
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "representative")
public class Representative extends BaseRepresentative {

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
