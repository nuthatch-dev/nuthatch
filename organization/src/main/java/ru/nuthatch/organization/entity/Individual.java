package ru.nuthatch.organization.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import ru.nuthatch.baseentity.entity.BaseIndividual;

@Entity
@Table(name = "individual")
public class Individual extends BaseIndividual implements BaseEntity {
}
