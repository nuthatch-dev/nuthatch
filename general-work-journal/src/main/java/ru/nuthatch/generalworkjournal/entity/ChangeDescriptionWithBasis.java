package ru.nuthatch.generalworkjournal.entity;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.AttributeOverrides;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;

/**
 * Описание комплексного типа: changeDescriptionWithBasis
 * Изменения в записях с указанием основания
 */
@Data
@Embeddable
@AttributeOverrides({
        @AttributeOverride(name = "description",
                column = @Column(name = "change_description_with_basis_description")),
        @AttributeOverride(name = "basis",
                column = @Column(name = "change_description_with_basis_basis"))
})
public class ChangeDescriptionWithBasis {

    /**
     * Внесённые изменения
     * Обязательный элемент
     * Минимум 1 символ
     */
    @Column(nullable = false)
    protected String description;

    /**
     * Основания для внесения данного изменения
     * Обязательный элемент
     * Минимум 1 символ
     */
    @Column(nullable = false)
    protected String basis;

}
