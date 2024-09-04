package ru.nuthatch.generalworkjournal.entity.representative;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import ru.nuthatch.baseentity.entity.BaseIndividualEntrepreneur;

/**
 * Описание комплексного типа: IndividualEntrepreneur.
 * Индивидуальный предприниматель
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "individual_entrepreneur")
public class IndividualEntrepreneur extends BaseIndividualEntrepreneur {

    /**
     * Информация о саморегулируемой организации
     */
    @ManyToOne
    protected Sro sro;

}
