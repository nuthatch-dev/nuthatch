package ru.nuthatch.generalworkjournal.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import ru.nuthatch.generalworkjournal.common.CommonEntity;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

/**
 * Описание комплексного типа: generalWorkJournalTitleChange
 * Подписываемая информация об изменениях титульного листа ОЖР
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "general_work_journal_title_change")
public class GeneralWorkJournalTitleChange extends CommonEntity implements Serializable {

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
