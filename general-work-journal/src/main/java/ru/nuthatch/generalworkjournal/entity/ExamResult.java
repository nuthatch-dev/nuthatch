package ru.nuthatch.generalworkjournal.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import ru.nuthatch.generalworkjournal.common.AttachedDocument;
import ru.nuthatch.generalworkjournal.common.CommonEntity;
import ru.nuthatch.generalworkjournal.common.DocInfo;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * Описание комплексного типа: examResult
 * Результат экспертиз, обследований, лабораторных и иных испытаний выполненных работ,
 * проведенных в процессе строительного контроля
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "exam_result")
public class ExamResult extends CommonEntity implements Serializable {

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
    @JoinTable(name = "exam_result_attached_document")
    protected Set<AttachedDocument> attachedDocumentSet = new HashSet<>();

    /**
     * Отношение к WorksPerformingInfo
     */
    @ManyToOne
    protected WorksPerformingInfo worksPerformingInfo;
}
