package ru.nuthatch.generalworkjournal.common;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.AttributeOverrides;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;

import java.util.Date;

/**
 * Описание комплексного типа: datetimeInterval
 * Дата и время начала и конца
 */
@Data
@Embeddable
public class DateTimeInterval {

    /**
     * Дата и время начала
     * Обязательный элемент
     * Дата в формате < ГГГГ-ММ-ДД-T-чч-мм-сс> (год-месяц-день-начало данных
     * времени-час-минута-секунда)
     */
    protected Date beginDate;

    /**
     * Дата и время конца
     * Обязательный элемент
     * Дата в формате < ГГГГ-ММ-ДД-T-чч-мм-сс> (год-месяц-день-начало данных
     * времени-час-минута-секунда)
     */
    protected Date endDate;
}
