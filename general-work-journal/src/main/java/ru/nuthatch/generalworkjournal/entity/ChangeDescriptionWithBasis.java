package ru.nuthatch.generalworkjournal.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;

/**
 * Описание комплексного типа: changeDescriptionWithBasis
 * Изменения в записях с указанием основания
 */
@Data
@Embeddable
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
