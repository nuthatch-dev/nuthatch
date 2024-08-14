package ru.nuthatch.projectdocumentation.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Единицы измерения величин ведомости объемов, спецификаций
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "unit")
public class Unit extends CommonEntity {

    protected String unit;
}
