package ru.nuthatch.generalworkjournal.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.util.UUID;

/**
 * Описание комплексного типа: WorkLocation
 * Тип, содержащий в себе информацию о местоположении
 */
@Data
@Entity
@Table(name = "work_location")
public class WorkLocation {

    @Id
    @GeneratedValue
    protected UUID uuid;

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
