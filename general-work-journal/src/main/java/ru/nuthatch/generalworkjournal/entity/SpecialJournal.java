package ru.nuthatch.generalworkjournal.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import ru.nuthatch.generalworkjournal.common.CommonEntity;

import java.io.Serializable;

/**
 * Описание комплексного типа: specialJournal
 * Подписываемая часть информации о спецжурнале
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "special_journal")
public class SpecialJournal extends CommonEntity implements Serializable {

    /**
     * Порядковый номер записи в перечне
     * Обязательный элемент
     * Целое положительное число
     */
    @Column(name = "sequence_number",
            nullable = false)
    protected int sequenceNumber;

    /**
     * Сведения о специальном журнале работ или о журнале авторского надзора
     * Обязательный элемент
     */
    @Embedded
    protected SpecialJournalInfo specialJournalInfo;

    /**
     * Отношение к общему журналу работ
     */
    @ManyToOne
    protected GeneralWorkJournal generalWorkJournal;
}
