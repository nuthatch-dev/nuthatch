package ru.nuthatch.generalworkjournal.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import ru.nuthatch.generalworkjournal.common.CommonEntity;
import ru.nuthatch.generalworkjournal.common.DocInfo;

import java.io.Serializable;
import java.util.Date;

/**
 * Описание комплексного типа: asBuiltSchema
 * Исполнительная схема или чертеж
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "as_built_schema")
public class AsBuiltSchema extends CommonEntity implements Serializable {

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
