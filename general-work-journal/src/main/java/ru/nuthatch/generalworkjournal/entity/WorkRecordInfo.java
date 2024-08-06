package ru.nuthatch.generalworkjournal.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;

import java.util.Date;
import java.util.UUID;

/**
 * Описание комплексного типа: workRecordInfo
 * Идентификатор записи
 */
@Data
@Embeddable
public class WorkRecordInfo {

    /**
     * Идентификатор записи в общем журнале
     * Обязательный элемент
     * Строгий формат:
     * хххххххх-хххх-хххх-хххх-хххххххххххх
     * Наложенные ограничения
     * [0-9a-f]{8}-[0-9a-f]{4}-[0-5][0-9a-f]{3}-[089ab][0-9a-f]{3}-[0-9a-f]{12}
     */
    @Column(name = "general_work_journal_record_id",
            nullable = false,
            updatable = false)
    protected UUID generalWorkJournalRecordId;

    /**
     * Дата выполнения работ
     * Обязательный элемент
     * Дата в формате <ГГГГ-ММ-ДД> (год-месяц-день)
     */
    @Column(name = "work_completion_date",
            nullable = false,
            updatable = false)
    protected Date workCompletionDate;
}
