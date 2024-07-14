package ru.nuthatch.generalworkjournal.entity;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.AttributeOverrides;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;

/**
 * Описание комплексного типа: journalVolume
 * Объём журнала
 */
@Data
@Embeddable
@AttributeOverrides({
        @AttributeOverride(name = "value",
                column = @Column(name = "journal_volume_value")),
        @AttributeOverride(name = "unit",
                column = @Column(name = "journal_volume_unit"))
})
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
