package ru.nuthatch.projectdocumentation.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedNativeQuery;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Описание комплексного типа Specification
 * Спецификация по проекту
 */
@NamedNativeQuery(name = "Specification.findAllMaterialData_Named",
        query = "SELECT * FROM material_data AS m WHERE m.specification_uuid = :uuid",
        resultClass = MaterialData.class)
@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "specification")
public class Specification extends CommonEntity {
    /*
     Шифр спецификации
     */
    protected String code;

    /*
     Список материалов, изделий и оборудования
     Named native query materialDataSet
     */

    /*
    Отношение к ProjectInfo
     */
    @ManyToOne
    protected ProjectInfo projectInfo;
}
