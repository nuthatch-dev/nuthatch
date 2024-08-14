package ru.nuthatch.projectdocumentation.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedNativeQuery;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Описание комплексного типа MaterialData
 * Данные по материалу спецификации
 */
@NamedNativeQuery(name = "MaterialData.findAllMaterialVolumeInfo_Named",
        query = "SELECT * FROM material_volume_info AS m WHERE m.material_data_uuid = :uuid",
        resultClass = MaterialVolumeInfo.class)
@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "material_data")
public class MaterialData extends CommonEntity {
    /*
     Характеристики материала/оборудования/изделия
     */
    @ManyToOne
    protected MaterialInfo materialInfo;

    /*
     Данные по объемам материала по отдельным ревизиям документации
     Named native query: materialVolumeInfoSet
     */

    /*
     Отношение к Specification
     */
    @ManyToOne
    protected Specification specification;

}
