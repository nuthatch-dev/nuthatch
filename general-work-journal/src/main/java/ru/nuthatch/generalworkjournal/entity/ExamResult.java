package ru.nuthatch.generalworkjournal.entity;

import jakarta.persistence.*;
import lombok.Data;
import ru.nuthatch.generalworkjournal.common.AttachedDocument;
import ru.nuthatch.generalworkjournal.common.DocInfo;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

/**
 * Описание комплексного типа: examResult
 * Результат экспертиз, обследований, лабораторных и иных испытаний выполненных работ,
 * проведенных в процессе строительного контроля
 */
@Data
@Entity
@Table(name = "exam_result")
public class ExamResult implements Serializable {

    @Id
    @GeneratedValue
    protected UUID uuid;

    @Embedded
    protected DocInfo docInfo;

    /**
     * Наименование экспертизы, обследования, испытания и т.п.
     * Необязательный элемент
     * Минимум 1 символ
     */
    @Column(name = "examination_name")
    protected String examinationName;

    /**
     * Информация о документе(документax), загруженном(загруженных) в хранилище
     * Обязательный элемент
     */
    @ManyToMany
    protected Set<AttachedDocument> attachedDocumentSet = new HashSet<>();

    /**
     * Отношение к WorksPerformingInfo
     */
    @ManyToOne
    protected WorksPerformingInfo worksPerformingInfo;
}
