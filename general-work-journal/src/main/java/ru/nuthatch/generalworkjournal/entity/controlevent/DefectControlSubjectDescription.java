package ru.nuthatch.generalworkjournal.entity.controlevent;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import ru.nuthatch.generalworkjournal.common.CommonEntity;
import ru.nuthatch.generalworkjournal.common.WorkLocation;

import java.io.Serializable;
import java.util.UUID;

/**
 * Описание предмета контроля (работа, ОКС или элемент ОКС)
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "defect_control_subject_description")
public class DefectControlSubjectDescription extends CommonEntity implements Serializable {

    /**
     * id расположения структурного элемента в иерархии
     */
    @Column(name = "structural_element_id")
    protected UUID structuralElementId;

    /**
     * Наименование и обозначение структурного элемента
     */
    @Column(name = "structural_element_name",
            nullable = false)
    protected String structuralElementName;

    /**
     * Местоположение результатов работ
     */
    @ManyToOne
    protected WorkLocation worksResultsLocation;
}
