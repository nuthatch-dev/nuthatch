package ru.nuthatch.baseentity.entity;

import jakarta.persistence.*;
import lombok.Data;
import ru.nuthatch.baseentity.Role;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

/**
 * Описание комплексного типа: Individual.
 * Физическое лицо
 */
@Data
@MappedSuperclass
public class BaseIndividual {

    @Id
    @GeneratedValue
    protected UUID uuid;

    /**
     * ФИО. Обязательный элемент
     */
    @Embedded
    protected FullNameGroup fullNameGroup;

    /**
     * Адрес. Обязательный элемент
     */
    @Column(nullable = false)
    protected String address;

    /**
     * Является гражданином РФ
     */
    @Column(name = "is_russian_federation_citizen",
            nullable = false)
    protected boolean isaRussianFederationCitizen = true;

    /**
     * Данные паспорта. Обязательный элемент
     */
    @Embedded
    protected PassportDetails passportDetails;

    /**
     * Роли, в качестве которых может выступать физическое лицо
     */
    @ElementCollection
    @Enumerated(EnumType.STRING)
    protected Set<Role> roleSet = new HashSet<>();

}
