package ru.nuthatch.generalworkjournal.entity.controlevent;

import jakarta.persistence.*;
import lombok.Data;
import ru.nuthatch.generalworkjournal.common.AttachedDocument;

import java.io.Serializable;
import java.util.UUID;

/**
 * Документ, подтверждающий факт нарушения
 */
@Data
@Entity
@Table(name = "defect_confirming_doc")
public class DefectConfirmingDoc implements Serializable {

    @Id
    @GeneratedValue
    protected UUID uuid;

    /**
     * Исполнительная схема или чертёж
     */
    @ManyToOne
    protected ExecutiveSchemeOrDrawing executiveSchemeOrDrawing;

    /**
     * Другой документ
     */
    @ManyToOne
    protected AttachedDocument anotherDoc;

    /**
     * Документ с результатами экспертиз, обследований, лабораторных и
     * иных испытаний выполненных работ
     */
    @ManyToOne
    protected ResultsDoc resultsDoc;

}
