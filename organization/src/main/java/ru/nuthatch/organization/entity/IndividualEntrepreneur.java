package ru.nuthatch.organization.entity;

import jakarta.persistence.*;
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
public class IndividualEntrepreneur extends BaseIndividualEntrepreneur implements BaseEntity {

    /**
     * Информация о саморегулируемой организации
     */
    @ManyToOne
    protected Sro sro;

}
