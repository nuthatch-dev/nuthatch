package ru.nuthatch.generalworkjournal.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.util.UUID;

/**
 * Описание комплексного типа: PermanentObjectInfo
 * Объект капительного строительства
 */
@Data
@Entity
@Table(name = "permanent_object_info")
public class PermanentObjectInfo implements Serializable {

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

    /**
     * UUID объекта капитального строительства
     * Обязательный элемент
     * Строгий формат:
     * хххххххх-хххх-хххx-хххx-хххххххххххх
     * Наложенные ограничения
     * [0-9a-f]{8}-[0-9a-f]{4}-[0-5][0-9a-f]{3}-[089ab][0-9a-f]{3}-[0-9a-f]{12}
     */
    @Id
    @GeneratedValue
    @Column(name = "permanent_object_uuid",
            nullable = false,
            updatable = false)
    protected UUID permanentObjectUUID;

}
