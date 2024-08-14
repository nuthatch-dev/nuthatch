package ru.nuthatch.projectdocumentation.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Описание комплексного типа MaterialInfo
 * Данные по применяемому материалу
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "material_info")
public class MaterialInfo extends CommonEntity {
    /*
     Позиция по спецификации
     */
    protected String positionNumber;

    /*
     Наименование и техническая характеристика
     */
    protected String name;

    /*
     Тип, марка, обозначение документа опросного листа
     */
    protected String mark;

    /*
     Код оборудования, изделия и материала
     */
    protected String productCode;

    /*
     Завод-изготовитель
     */
    protected String supplier;

    /*
     Единицы измерения
     */
    @ManyToOne
    protected Unit unit;

    /*
     Масса единицы, кг
     */
    protected String weight;

    /*
     Примечание
     */
    protected String description;


    /*
    Отношение к MaterialData
     */
    @ManyToOne
    protected MaterialData materialData;

}
