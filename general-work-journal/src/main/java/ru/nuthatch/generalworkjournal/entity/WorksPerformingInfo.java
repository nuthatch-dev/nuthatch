package ru.nuthatch.generalworkjournal.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import ru.nuthatch.generalworkjournal.common.CommonEntity;

import java.io.Serializable;
import java.util.UUID;

/**
 * Описание комплексного типа: worksPerformingInfo
 * Сведения о выполнении работ в процессе строительства, реконструкции,
 * капитального ремонта объекта капитального строительства (id)
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "works_performing_info")
public class WorksPerformingInfo extends CommonEntity implements Serializable {

    /**
     * Идентификатор записи
     * Обязательный элемент
     */
    @Embedded
    protected WorkRecordInfo workRecordInfo;

    /*
     Наименование работ, выполняемых в процессе строительства, реконструкции,
     капитального ремонта объекта капитального строительства
     Обязательный элемент (одно значение из элементов типа)
     TODO: Список constructionWorksInfoListItem (native query)
     */

    /*
    Сведения о проведенных испытаниях конструкций, оборудования, систем, сетей и
    устройств (опробование вхолостую или под нагрузкой, подача электроэнергии, давления,
    испытания на прочность и герметичность и др.)
    Обязательный элемент (одно значение из элементов типа)
    TODO: Список testingsInfoListItem (native query)
     */

    /*
    Список исполнительных схем и чертежей
    Необязательный элемент
    TODO: Список asBuiltSchema (native query)
     */

    /*
    Результаты экспертиз, обследований, лабораторных и иных испытаний
    выполненных работ, проведенных в процессе строительного контроля
    Необязательный элемент
    TODO: Список examResult (native query)
     */

    /*
    Список разделов ПД и/или РД, на основании которых выполнялась работа
    Обязательный элемент
    TODO: Список workAndProjectDocumentation (native query)
     */

    /*
    Сведения о применяемых строительных материалах, изделиях и конструкциях
    Необязательный элемент
    TODO: Список usedMaterial (native query)
     */

    /*
    НТД-1
    Обязательный элемент
    TODO: Список technicalRegulationDetail (native query)
     */

    /**
     * Сведения о представителе лица, осуществляющего строительство,
     * уполномоченном на внесение записей в ОЖР
     */
    @Column(nullable = false)
    protected UUID representative;

    /**
     * Отношение к общему журналу работ
     */
    @ManyToOne
    protected GeneralWorkJournal generalWorkJournal;

}
