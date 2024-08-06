package ru.nuthatch.generalworkjournal.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;

/**
 * Описание комплексного типа: journalVolume
 * Объём журнала
 */
@Data
@Embeddable
public class JournalVolume {

    /**
     * Количество в единицах измерения объёма
     * Обязательный элемент
     * Целое положительное число
     */
    @Column(nullable = false)
    protected int value;

    /**
     * Наименование единицы измерения объёма
     * Обязательный элемент
     * Минимум 1 символ
     */
    @Column(nullable = false)
    protected String unit;
}
