package ru.nuthatch.projectdocumentation.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedNativeQuery;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Описание комплексного типа VolumeSheet
 * Ведомость объемов работ
 */
@NamedNativeQuery(name = "VolumeSheet.findAllWorkData_Named",
        query = "SELECT * FROM work_data AS w WHERE w.volume_sheet_uuid = :uuid",
        resultClass = WorkData.class)
@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "volume_sheet")
public class VolumeSheet extends CommonEntity {
    /*
     Шифр ведомости
     */
    protected String code;

    /*
     Список работ по ведомости по отдельным ревизиям проекта
     Named native query workDataSet
     */

    /*
    Отношение к ProjectInfo
     */
    @ManyToOne
    protected ProjectInfo projectInfo;
}
