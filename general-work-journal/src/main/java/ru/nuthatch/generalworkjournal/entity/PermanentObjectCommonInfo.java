package ru.nuthatch.generalworkjournal.entity;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.AttributeOverrides;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;

import java.util.Date;

/**
 * Описание комплексного типа: permanentObjectCommonInfo
 * Общие сведения об объекте капитального строительства
 */
@Data
@Embeddable
@AttributeOverrides({
        @AttributeOverride(name = "project_characteristics",
                column = @Column(name = "permanent_object_common_info_project_characteristics")),
        @AttributeOverride(name = "construction_start_date",
                column = @Column(name = "permanent_object_common_info_construction_start_date")),
        @AttributeOverride(name = "construction_end_date",
                column = @Column(name = "permanent_object_common_info_construction_end_date")),
})
public class PermanentObjectCommonInfo {

    /**
     * Краткая проектная характеристика
     * Обязательный элемент
     * Минимум 1 символ
     */
    protected String projectCharacteristics;

    /**
     * Дата начала строительства, реконструкции, капитального ремонта объекта
     * капитального строительства
     * Обязательный элемент
     * Дата в формате <ГГГГ-ММ-ДД> (год-месяц-день)
     */
    protected Date constructionStartDate;

    /**
     * Дата окончания строительства, реконструкции, капитального ремонта
     * объекта капитального строительства
     * Необязательный элемент
     * Дата в формате <ГГГГ-ММ-ДД> (год-месяц-день)
     */
    protected Date constructionEndDate;
}
