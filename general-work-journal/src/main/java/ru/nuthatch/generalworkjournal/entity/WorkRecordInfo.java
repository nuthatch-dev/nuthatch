package ru.nuthatch.generalworkjournal.entity;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.AttributeOverrides;
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
@AttributeOverrides({
        @AttributeOverride(name = "general_work_journal_record_id",
                column = @Column(name = "work_record_info_general_work_journal_record_id")),
        @AttributeOverride(name = "work_completion_date",
                column = @Column(name = "work_record_info_work_completion_date"))
})
public class WorkRecordInfo {

    /**
     * Идентификатор записи в общем журнале
     * Обязательный элемент
     * Строгий формат:
     * хххххххх-хххх-хххх-хххх-хххххххххххх
     * Наложенные ограничения
     * [0-9a-f]{8}-[0-9a-f]{4}-[0-5][0-9a-f]{3}-[089ab][0-9a-f]{3}-[0-9a-f]{12}
     */
    @Column(nullable = false,
            updatable = false)
    protected UUID generalWorkJournalRecordId;

    /**
     * Дата выполнения работ
     * Обязательный элемент
     * Дата в формате <ГГГГ-ММ-ДД> (год-месяц-день)
     */
    @Column(nullable = false,
            updatable = false)
    protected Date workCompletionDate;
}
