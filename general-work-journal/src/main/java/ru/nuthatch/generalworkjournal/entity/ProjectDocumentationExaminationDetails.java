package ru.nuthatch.generalworkjournal.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;

import java.util.UUID;

/**
 * Описание комплексного типа: ProjectDocumentationExaminationDetails
 * Сведения о государственной экспертизе проектной документации в случаях, предусмотренных
 * статьей 49 Градостроительного кодекса Российской Федерации
 */
@Data
@Embeddable
public class ProjectDocumentationExaminationDetails {

    /**
     * Порядковый номер
     * Обязательный элемент
     * Целое положительное число
     */
    @Column(name = "sequence_number")
    protected int sequenceNumber;

    /**
     * Реквизиты заключения экспертизы проектной документации и
     * результатов инженерных изысканий
     * Обязательный элемент
     */
    @Column(name = "expertise_conclusion_requisites")
    protected UUID expertiseConclusionRequisites;

    /**
     * Наименование органа исполнительной власти или органа местного
     * самоуправления (организации), выдавшего документ
     * Обязательный элемент
     * Минимум 1 символ
     */
    @Column(name = "executive_authority_name")
    protected String executiveAuthorityName;
}
