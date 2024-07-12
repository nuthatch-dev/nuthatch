package ru.nuthatch.generalworkjournal.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.UUID;

/**
 * Описание комплексного типа: specialJournal
 * Подписываемая часть информации о спецжурнале
 */
@Data
@Entity
@Table(name = "special_journal")
public class SpecialJournal implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * Id подписываемой информации о спецжурнале
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
