package ru.nuthatch.generalworkjournal.entity.controlevent;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import ru.nuthatch.generalworkjournal.common.AttachedDocument;
import ru.nuthatch.generalworkjournal.common.CommonEntity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * Описание недостатка
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "defect_description")
public class DefectDescription extends CommonEntity implements Serializable {

    /**
     * Формулировка выявленного недостатка
     */
    @Column(name = "defect_description_text",
            nullable = false)
    protected String defectDescriptionText;

    /**
     * Перечень технических регламентов, иных нормативных правовых актов,
     * требования которых нарушены
     */
    @ManyToMany
    @JoinTable(name = "defect_description_tecnical_regulations")
    protected Set<TechnicalRegulations> technicalRegulationsSet = new HashSet<>();

    /**
     * Перечень разделов проектной документации, требования которых нарушены
     */
    @ManyToMany
    @JoinTable(name = "defect_description_project_doc")
    protected Set<ProjectDocSectionsSetItem> projectDocSectionsSet = new HashSet<>();

    /**
     * Перечень разделов рабочей документации, требования которых нарушены
     */
    @ManyToMany
    @JoinTable(name = "defect_description_working_doc")
    protected Set<WorkingDocSectionsSetItem> workingDocSectionsSet = new HashSet<>();

    /**
     * Дополнительные прилагаемые документы (список файлов)
     */
    @ManyToMany
    @JoinTable(name = "defect_description_attached_document")
    protected Set<AttachedDocument> attachedDocumentSet = new HashSet<>();

    /**
     * Список документов, подтверждающих факт нарушения
     */
    @ManyToMany
    @JoinTable(name = "defect_description_confirming_doc")
    protected Set<DefectConfirmingDoc> defectConfirmingDocSet = new HashSet<>();
}
