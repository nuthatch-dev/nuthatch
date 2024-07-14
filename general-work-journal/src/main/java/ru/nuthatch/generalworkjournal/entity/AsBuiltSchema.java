package ru.nuthatch.generalworkjournal.entity;

import jakarta.persistence.*;
import lombok.Data;
import ru.nuthatch.generalworkjournal.common.DocInfo;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

/**
 * Описание комплексного типа: asBuiltSchema
 * Исполнительная схема или чертеж
 */
@Data
@Entity
@Table(name = "as_built_schema")
public class AsBuiltSchema implements Serializable {

    /**
     * Id документа
     * Обязательный элемент
     * Строгий формат:
     * хххххххх-хххх-хххх-хххх-хххххххххххх
     * Наложенные ограничения
     * [0-9a-f]{8}-[0-9a-f]{4}-[0-5][0-9a-f]{3}-[089ab][0-9a-f]{3}-[0-9a-f]{12}
     */
    @Id
    @GeneratedValue
    protected UUID uuid;

    /**
     * Дата документа
     * Необязательный элемент
     * Дата в формате <ГГГГ-ММ-ДД> (год-месяц-день)
     */
    protected Date date;

    /**
     * Идентификатор документа
     */
    @Embedded
    protected DocInfo docInfo;

    /**
     * Отношение к WorksPerformingInfo
     */
    @ManyToOne
    protected WorksPerformingInfo worksPerformingInfo;
}
