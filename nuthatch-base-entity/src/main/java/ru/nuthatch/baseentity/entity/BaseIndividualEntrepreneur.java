package ru.nuthatch.baseentity.entity;

import jakarta.persistence.*;
import lombok.Data;
import ru.nuthatch.baseentity.Role;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

/**
 * Описание комплексного типа: IndividualEntrepreneur.
 * Индивидуальный предприниматель
 */
@Data
@MappedSuperclass
public class BaseIndividualEntrepreneur implements Serializable {

    @Id
    @GeneratedValue
    protected UUID uuid;

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
     * Роли, в качестве которых может выступать индивидуальный предприниматель
     */
    @ElementCollection
    @Enumerated(EnumType.STRING)
    protected Set<Role> roleSet = new HashSet<>();
}
