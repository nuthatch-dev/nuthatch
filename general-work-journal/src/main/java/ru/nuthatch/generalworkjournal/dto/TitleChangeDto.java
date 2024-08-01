package ru.nuthatch.generalworkjournal.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;
import java.util.UUID;

@Data
@AllArgsConstructor
public class TitleChangeDto {

    protected UUID uuid;

    /**
     * Порядковый номер записи
     * Обязательный элемент
     * Неотрицательное целое число
     */
    protected int sequenceNumber;

    /**
     * Дата внесения изменения
     * Обязательный элемент
     * Дата в формате <ГГГГ-ММ-ДД> (год-месяц-день)
     */
    protected Date changeDate;

    /**
     * Изменения в записях с указанием основания
     * Обязательный элемент
     */
    protected String basis;

    protected String description;

    /**
     * Представитель, ответственный за внесение изменений и ведение записей о них
     * Обязательный элемент
     */
    protected UUID responsibleRepresentative;

}
