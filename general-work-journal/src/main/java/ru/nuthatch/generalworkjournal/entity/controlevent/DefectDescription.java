package ru.nuthatch.generalworkjournal.entity.controlevent;

import jakarta.persistence.*;
import lombok.Data;
import ru.nuthatch.generalworkjournal.common.AttachedDocument;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

/**
 * Описание недостатка
 */
@Data
@Entity
@Table(name = "defect_description")
public class DefectDescription implements Serializable {

    @Id
    @GeneratedValue
    protected UUID uuid;

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
    protected Set<TechnicalRegulations> technicalRegulationsSet = new HashSet<>();

    /**
     * Перечень разделов проектной документации, требования которых нарушены
     */
    @ManyToMany
    protected Set<ProjectDocSectionsSetItem> projectDocSectionsSet = new HashSet<>();

    /**
     * Перечень разделов рабочей документации, требования которых нарушены
     */
    @ManyToMany
    protected Set<WorkingDocSectionsSetItem> workingDocSectionsSet = new HashSet<>();

    /**
     * Дополнительные прилагаемые документы (список файлов)
     */
    @ManyToMany
    protected Set<AttachedDocument> attachedDocumentSet = new HashSet<>();

    /**
     * Список документов, подтверждающих факт нарушения
     */
    @ManyToMany
    protected Set<DefectConfirmingDoc> defectConfirmingDocSet = new HashSet<>();
}
