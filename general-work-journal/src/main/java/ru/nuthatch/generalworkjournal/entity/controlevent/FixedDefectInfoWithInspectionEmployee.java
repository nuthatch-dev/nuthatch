package ru.nuthatch.generalworkjournal.entity.controlevent;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import ru.nuthatch.generalworkjournal.common.CommonEntity;
import ru.nuthatch.generalworkjournal.common.Representative;

import java.io.Serializable;
import java.util.Date;

/**
 * Информация о недостатке (предмет контроля,
 * описание недостатка, плановая дата его устранения, атрибуты)
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "fixed_defect_info_with_inspection_employee")
public class FixedDefectInfoWithInspectionEmployee extends CommonEntity implements Serializable {

    /**
     * Дата внесения записи о недостатке
     */
    @Column(name = "defect_record_date",
            nullable = false)
    protected Date defectRecordDate;

    /**
     * Номер недостатка
     * Положительное целое число
     */
    @Column(name = "defect_number",
            nullable = false)
    protected int defectNumber;

    /**
     * Описание предмета контроля (работа, ОКС или элемент ОКС)
     */
    @OneToOne
    protected DefectControlSubjectDescription defectControlSubjectDescription;

    /**
     * Описание недостатка
     */
    @OneToOne
    protected DefectDescription defectDescription;

    /**
     * Установленная (запланированная) дата устранения выявленного недостатка
     */
    @Column(name = "detect_fixing_plan_date",
            nullable = false)
    protected Date defectFixingPlanDate;

    /**
     * Сведения о сотруднике, проводившем контроль
     */
    @ManyToOne
    protected Representative inspectionEmployee;

    /**
     * Фактическая дата устранения выявленного недостатка
     */
    @Column(name = "defect_fixing_fact_date")
    protected Date defectFixingFactDate;

    /**
     * Сведения о сотруднике, проводившем контроль устранения недостатка
     */
    @ManyToOne
    protected Representative defectFixingInspectionEmployee;
}
