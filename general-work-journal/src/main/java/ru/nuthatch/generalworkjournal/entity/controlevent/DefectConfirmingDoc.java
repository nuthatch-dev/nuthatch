package ru.nuthatch.generalworkjournal.entity.controlevent;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import ru.nuthatch.generalworkjournal.common.AttachedDocument;
import ru.nuthatch.generalworkjournal.common.CommonEntity;

import java.io.Serializable;

/**
 * Документ, подтверждающий факт нарушения
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "defect_confirming_doc")
public class DefectConfirmingDoc extends CommonEntity implements Serializable {

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
