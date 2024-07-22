package ru.nuthatch.generalworkjournal.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import ru.nuthatch.generalworkjournal.common.CommonEntity;
import ru.nuthatch.generalworkjournal.common.WorkLocation;

import java.io.Serializable;
import java.util.UUID;

/**
 * Описание комплексного типа: workOrTestInfo
 * Информация о работе / испытании, выполняемой(-ом) в процессе
 * строительства/реконструкции/капитального ремонта объекта капитального строительства
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "work_or_test_info")
public class WorkOrTestInfo extends CommonEntity implements Serializable {

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
