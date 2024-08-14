package ru.nuthatch.projectdocumentation.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Описание комплексного типа WorkInfo
 * Вид работ
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "work_info")
public class WorkInfo extends CommonEntity {
    /*
     Порядковый номер
     */
    protected String sheetNumber;

    /*
     Номер локального сметного расчета
     */
    protected String localEstimateNumber;

    /*
     Наименование работ
     */
    protected String name;

    /*
     Единица измерения
     */
    @ManyToOne
    protected Unit unit;

    /*
     Примечание
     */
    protected String description;

}
