package ru.nuthatch.generalworkjournal.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

/**
 * Описание комплексного типа: generalWorkJournalTitleChange
 * Подписываемая информация об изменениях титульного листа ОЖР
 */
@Data
@Entity
@Table(name = "general_work_journal_title_change")
public class GeneralWorkJournalTitleChange implements Serializable {

    /**
     * Id подписываемой информация об изменениях титульного листа ОЖР
     * Обязательный элемент
     * Строгий формат:
     * _хххххххх-хххх-хххх-хххх-хххххххххххх
     * Наложенные ограничения
     * _[0-9a-f]{8}-[0-9a-f]{4}-[0-5][0-9a-f]{3}-[089ab][0-9a-f]{3}-[0-9a-f]{12}
     */
    @Id
    @GeneratedValue
    protected UUID uuid;

    /**
     * Порядковый номер записи
     * Обязательный элемент
     * Неотрицательное целое число
     */
    @Column(name = "sequence_number",
            nullable = false)
    protected int sequenceNumber;

    /**
     * Дата внесения изменения
     * Обязательный элемент
     * Дата в формате <ГГГГ-ММ-ДД> (год-месяц-день)
     */
    @Column(name = "change_date",
            nullable = false)
    protected Date changeDate;

    /**
     * Изменения в записях с указанием основания
     * Обязательный элемент
     */
    @Embedded
    protected ChangeDescriptionWithBasis changeDescriptionWithBasis;

    /**
     * Представитель, ответственный за внесение изменений и ведение записей о них
     * Обязательный элемент
     */
    @Column(name = "responsible_representative",
            nullable = false)
    protected UUID responsibleRepresentative;

    /**
     * Отношение записи об изменении к журналу
     */
    @ManyToOne
    protected GeneralWorkJournal generalWorkJournal;
}
