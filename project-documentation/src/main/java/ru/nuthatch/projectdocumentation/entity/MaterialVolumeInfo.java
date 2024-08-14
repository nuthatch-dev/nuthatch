package ru.nuthatch.projectdocumentation.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Описание комплексного типа MaterialVolumeInfo
 * Данные по объему материала для соответствующего изменения
 * проектной документации
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "material_volume_info")
public class MaterialVolumeInfo extends CommonEntity {
    /*
     Действующая на данный момент ревизия проекта
     */
    protected boolean isActive;

    /*
     Номер ревизии
     */
    @ManyToOne
    protected RevisionInfo revisionInfo;

    /*
     Количество
     */
    protected String value;

    /*
    Отношение к MaterialData
     */
    @ManyToOne
    protected MaterialData materialData;
}
