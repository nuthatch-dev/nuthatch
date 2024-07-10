package ru.nuthatch.generalworkjournal.common;

import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;


/**
 * Описание комплексного типа: baseDocument
 * Информация об UUID и редакции документа,
 * версии схемы
 */
@Data
@Embeddable
@Table(name = "base_document")
public class BaseDocument {

    /**
     * Версия схемы
     * Обязательный элемент, заполняется шиной
     */
    @Column(name = "schema_version")
    protected String schemaVersion;

    /**
     * UUID документа
     * Обязательный элемент
     * Строгий формат:
     * хххххххх-хххх-хххx-хххx-хххххххххххх
     * Наложенные ограничения
     * [0-9a-f]{8}-[0-9a-f]{4}-[0-5][0-9a-f]{3}-[089ab][0-9a-f]{3}-[0-9a-f]{12}
     */
    @GeneratedValue
    @Column(name = "document_uuid",
            nullable = false,
            updatable = false)
    protected UUID uuid;

    /**
     * Редакция документа (версия)
     * Обязательный элемент, заполняется ИС
     */
    protected int edition;

}
