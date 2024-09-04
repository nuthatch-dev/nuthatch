package ru.nuthatch.organization.entity;

import jakarta.persistence.*;
import ru.nuthatch.baseentity.entity.BaseIndividual;

/**
 * Описание комплексного типа: Individual.
 * Физическое лицо
 */
@Entity
@Table(name = "individual")
public class Individual extends BaseIndividual implements BaseEntity {

}
