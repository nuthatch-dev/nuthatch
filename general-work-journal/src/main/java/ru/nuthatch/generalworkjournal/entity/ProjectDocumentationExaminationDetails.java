package ru.nuthatch.generalworkjournal.entity;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.AttributeOverrides;
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
@AttributeOverrides({
        @AttributeOverride(name = "sequence_number",
                column = @Column(name = "project_documentation_examination_details_sequence_number")),
        @AttributeOverride(name = "expertise_conclusion_requisites",
                column = @Column(name = "project_documentation_examination_details_expertise_conclusion_requisites")),
        @AttributeOverride(name = "executive_authority_name",
                column = @Column(name = "project_documentation_examination_details_executive_authority_name"))
})
public class ProjectDocumentationExaminationDetails {

    /**
     * Порядковый номер
     * Обязательный элемент
     * Целое положительное число
     */
    protected int sequenceNumber;

    /**
     * Реквизиты заключения экспертизы проектной документации и
     * результатов инженерных изысканий
     * Обязательный элемент
     */
    protected UUID expertiseConclusionRequisites;

    /**
     * Наименование органа исполнительной власти или органа местного
     * самоуправления (организации), выдавшего документ
     * Обязательный элемент
     * Минимум 1 символ
     */
    protected String executiveAuthorityName;
}
