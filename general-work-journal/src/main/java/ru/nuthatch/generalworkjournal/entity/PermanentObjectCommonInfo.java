package ru.nuthatch.generalworkjournal.entity;

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
public class PermanentObjectCommonInfo {

    /**
     * Краткая проектная характеристика
     * Обязательный элемент
     * Минимум 1 символ
     */
    @Column(name = "project_characteristics", nullable = false)
    protected String projectCharacteristics;

    /**
     * Дата начала строительства, реконструкции, капитального ремонта объекта
     * капитального строительства
     * Обязательный элемент
     * Дата в формате <ГГГГ-ММ-ДД> (год-месяц-день)
     */
    @Column(name = "construction_start_date", nullable = false)
    protected Date constructionStartDate;

    /**
     * Дата окончания строительства, реконструкции, капитального ремонта
     * объекта капитального строительства
     * Необязательный элемент
     * Дата в формате <ГГГГ-ММ-ДД> (год-месяц-день)
     */
    @Column(name = "construction_end_date")
    protected Date constructionEndDate;
}
