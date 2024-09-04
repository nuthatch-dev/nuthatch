package ru.nuthatch.organization.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.util.UUID;

/**
 * Описание комплексного типа: Sro.
 * Группа, содержащая информацию о Саморегулируемой организации (СРО)
 */
@Data
@Entity
@Table(name = "sro")
public class Sro implements Serializable, BaseEntity {

    @Id
    @GeneratedValue
    protected UUID uuid;

    /**
     * Наименование.
     * Обязательный элемент
     */
    @Column(nullable = false)
    protected String name;

    /**
     * Идентификационный номер налогоплательщика.
     * Обязательный элемент
     * хххххххххх(хх)
     * 10 обязательных и 2 необязательных
     */
    @Column(nullable = false, length = 12)
    protected String inn;

    /**
     * Основной государственный регистрационный номер.
     * Обязательный элемент
     * Формат:
     * ххххххххххххх
     * 13 цифр
     */
    @Column(nullable = false, length = 13)
    protected String ogrn;
}
