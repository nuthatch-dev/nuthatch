package ru.nuthatch.generalworkjournal.entity;

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
    @Column(name = "document_name",
            nullable = false)
    protected String name;

    /**
     * Номер документа
     * Обязательный элемент
     * Минимум 1 символ
     */
    @Column(name = "document_number",
            nullable = false)
    protected String number;

}
