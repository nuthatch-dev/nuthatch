package ru.nuthatch.generalworkjournal.common;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * Описание комплексного типа: WorkLocation
 * Тип, содержащий в себе информацию о местоположении
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "work_location")
public class WorkLocation extends CommonEntity implements Serializable {

    // Оси
    protected String axes;

    // Ряды
    protected String ranks;

    // Отметки
    protected String marks;

    // Этажи
    protected String floors;

    // Ярусы
    protected String tiers;

    // Секции
    protected String sections;

    // Помещения
    protected String premises;

    /**
     * Местоположение, описанное иным способом
     * Обязательный элемент
     * в случае, если поля "оси, ряды, отметки, этажи, ярусы, секции, помещения"
     * не содержат данных
     * Минимум 1 символ
     */
    protected String place;
}
