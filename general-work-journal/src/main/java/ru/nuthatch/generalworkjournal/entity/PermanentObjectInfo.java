package ru.nuthatch.generalworkjournal.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import ru.nuthatch.generalworkjournal.common.CommonEntity;

import java.io.Serializable;
import java.util.UUID;

/**
 * Описание комплексного типа: PermanentObjectInfo
 * Объект капительного строительства
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "permanent_object_info")
public class PermanentObjectInfo extends CommonEntity implements Serializable {

    /**
     * Наименование объекта (этапа)
     * Обязательный элемент
     * Минимум 1 символ
     */
    @Column(name = "permanent_object_name",
            nullable = false)
    protected String permanentObjectName;

    /**
     * Информация об адресе обьекта (Почтовом или строительном)
     * Обязательный элемент
     */
    @Embedded
    @Column(name = "permanent_object_address",
            nullable = false)
    protected PostalOrConstructionSiteAddress permanentObjectAddress;

}
