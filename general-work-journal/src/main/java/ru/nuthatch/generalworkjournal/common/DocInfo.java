package ru.nuthatch.generalworkjournal.common;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.AttributeOverrides;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;

/**
 * Описание комплексного типа: generalWorkJournal/docInfo
 * Идентификатор документа
 */
@Data
@Embeddable
public class DocInfo {

    /**
     * Наименование документа
     * Обязательный элемент
     * Минимум 1 символ
     */
    @Column(nullable = false)
    protected String name;

    /**
     * Номер документа
     * Обязательный элемент
     * Минимум 1 символ
     */
    @Column(nullable = false)
    protected String number;

}
