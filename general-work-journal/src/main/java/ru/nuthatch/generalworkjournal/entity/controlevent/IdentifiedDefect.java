package ru.nuthatch.generalworkjournal.entity.controlevent;

import jakarta.persistence.*;
import lombok.Data;
import ru.nuthatch.generalworkjournal.common.AttachedDocument;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

/**
 * Недостаток предмета освидетельствования
 */
@Data
@Entity
@Table(name = "identified_defect")
public class IdentifiedDefect implements Serializable {

    @Id
    @GeneratedValue
    protected UUID uuid;

    /**
     * Информация о недостатке (предмет контроля,
     * описание недостатка, плановая дата его устранения, атрибуты)
     */
    @OneToOne
    protected FixedDefectInfoWithInspectionEmployee fixedDefectInfoWithInspectionEmployee;

    /**
     * Список документов, подтверждающих факт устранения недостатка
     */
    @ManyToMany
    protected Set<DefectConfirmingDoc> fixingDefectConfirmationDocSet = new HashSet<>();

    /**
     * Массив файлов с обосновывающими материалами
     */
    @ManyToMany
    protected Set<AttachedDocument> attachedDocumentSet = new HashSet<>();

    /**
     * Id акта контрольного мероприятия, в котором зафиксировано наличие недостатка
     */
    @Column(name = "identified_defect_act_id")
    protected UUID identifiedDefectActId;

    /**
     * Указание на необходимость внесения недостатка в общий журнал работ
     * (логический тип)
     */
    protected boolean defectRecordInGeneralWorkJournal = false;

    /**
     * Id акта контрольного мероприятия, в котором зафиксировано устранение недостатка
     */
    @Column(name = "fixed_defect_act_id")
    protected UUID fixedDefectActId;
}
