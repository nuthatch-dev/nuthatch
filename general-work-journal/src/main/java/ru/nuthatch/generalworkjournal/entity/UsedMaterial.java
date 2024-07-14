package ru.nuthatch.generalworkjournal.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

/**
 * Описание комплексного типа: UsedMaterial
 * Тип, содержащий в себе информацию о материале
 */
@Data
@Entity
@Table(name = "used_material")
public class UsedMaterial implements Serializable {

    @Id
    @GeneratedValue
    protected UUID uuid;

    /**
     * Наименование материала (изделия), его маркировка
     * Обязательный элемент
     * Минимум 1 символ
     */
    @Column(nullable = false)
    protected String name;

    /**
     * id записи журнала входного контроля материалов
     * Обязательный элемент
     * Строгий формат:
     * хххххххх-хххх-хххх-хххх-хххххххххххх
     * Наложенные ограничения
     * [0-9a-f]{8}-[0-9a-f]{4}-[0-5][0-9a-f]{3}-[089ab][0-9a-f]{3}-[0-9a-f]{12}
     */
    @Column(name = "internal_material_control_record_id",
            nullable = false)
    protected UUID internalMaterialControlRecordId;

    /**
     * Комплект ДПК на указанный материал (изделие)
     * Необязательный элемент
     * TODO: Взаимодействие с отдельным сервисом "Журнал входного контроля материалов и изделий"
     */
    @ElementCollection
    protected Set<UUID> qualityApproveDocuments = new HashSet<>();

    /**
     * Отношение к WorksPerformingInfo
     */
    @ManyToOne
    protected WorksPerformingInfo worksPerformingInfo;
}
