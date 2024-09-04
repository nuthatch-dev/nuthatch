package ru.nuthatch.organization.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import ru.nuthatch.baseentity.entity.BaseSro;

/**
 * Описание комплексного типа: Sro.
 * Группа, содержащая информацию о Саморегулируемой организации (СРО)
 */
@Entity
@Table(name = "sro")
public class Sro extends BaseSro implements BaseEntity {
}
