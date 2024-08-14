package ru.nuthatch.projectdocumentation.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedNativeQuery;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Описание комплексного типа WorkData
 * Данные по выполняемой работе
 */
@NamedNativeQuery(name = "WorkData.findAllWorkVolumeInfo_Named",
        query = "SELECT * FROM work_volume_info AS w WHERE w.work_data_uuid = :uuid",
        resultClass = WorkVolumeInfo.class)
@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "work_data")
public class WorkData extends CommonEntity {
    /*
     Характеристика выполняемой работы
     */
    @ManyToOne
    protected WorkInfo workInfo;

    /*
     Объемы выполняемые по отдельным ревизиям проектной ведомости работ
     Named native query workVolumeInfoSet
     */

    /*
    Отношение к VolumeSheet
     */
    @ManyToOne
    protected VolumeSheet volumeSheet;
}
