package ru.nuthatch.projectdocumentation.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Описание комплексного типа WorkVolumeInfo
 * Данные по объему работ
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "work_volume_info")
public class WorkVolumeInfo extends CommonEntity {
    /*
     Действующая ревизия проекта
     */
    protected boolean isActive;

    /*
     Порядковый номер ревизии
     */
    @ManyToOne
    protected RevisionInfo revisionInfo;

    /*
     Объем работ
     */
    protected String value;

    /*
     Ссылка на чертеж
     */
    protected String drawingReference;

    /*
     Формула расчета
     */
    protected String calculationFormula;

    /*
    Отношение к WorkData
     */
    @ManyToOne
    protected WorkData workData;

}
