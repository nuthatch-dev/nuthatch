package ru.nuthatch.generalworkjournal.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.util.UUID;

/**
 * Описание комплексного типа: workOrTestInfo
 * Информация о работе / испытании, выполняемой(-ом) в процессе
 * строительства/реконструкции/капитального ремонта объекта капитального строительства
 */
@Data
@Entity
@Table(name = "work_or_test_info")
public class WorkOrTestInfo implements Serializable {

    @Id
    @GeneratedValue
    protected UUID uuid;

    /**
     * Тип, содержащий в себе описание работы
     * Обязательный элемент
     */
    @Embedded
    protected HiddenWorkInfo hiddenWorkInfo;

    /**
     * Местоположение
     * Обязательный элемент
     */
    @OneToOne
    protected WorkLocation location;

    /**
     * Идентификатор записи в спецжурнале работ или иного документа подтверждающего качество
     * Необязательный элемент
     * Строгий формат:
     * хххххххх-хххх-хххх-хххх-хххххххххххх
     * Наложенные ограничения
     * [0-9a-f]{8}-[0-9a-f]{4}-[0-5][0-9a-f]{3}-[089ab][0-9a-f]{3}-[0-9a-f]{12}
     */
    @Column(name = "quality_assurance_record_id")
    protected UUID qualityAssuranceRecordId;

    /**
     * Отношение к WorksPerformingInfo
     */
    @ManyToOne
    protected WorksPerformingInfo worksPerformingInfo;
}
