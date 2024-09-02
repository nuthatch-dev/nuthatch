package ru.nuthatch.organization.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.HashSet;
import java.util.Set;

/**
 * Описание комплексного типа: IndividualEntrepreneur.
 * Индивидуальный предприниматель
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "individual_entrepreneur")
public class IndividualEntrepreneur extends CommonEntity {

    /**
     * ФИО. Обязательный элемент
     */
    @Embedded
    protected FullNameGroup fullNameGroup;

    /**
     * Адрес (Почтовый). Обязательный элемент
     */
    @Column(nullable = false)
    protected String address;

    /**
     * Основной государственный регистрационный номер индивидуального предпринимателя.
     * Обязательный элемент.
     * Формат:
     * ххххххххххххх
     * 13 цифр
     */
    @Column(nullable = false, length = 13)
    protected String ogrnip;

    /**
     * ИНН.
     * Обязательный элемент
     * хххххххххххх
     * 12 цифр
     */
    @Column(nullable = false, length = 12)
    protected String inn;

    /**
     * Информация о саморегулируемой организации
     */
    @ManyToOne
    protected Sro sro;

    /**
     * Роли, в качестве которых может выступать индивидуальный предприниматель
     */
    @ElementCollection
    protected Set<Role> roleSet = new HashSet<>();
}
