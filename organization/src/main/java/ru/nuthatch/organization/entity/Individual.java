package ru.nuthatch.organization.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.HashSet;
import java.util.Set;

/**
 * Описание комплексного типа: Individual.
 * Физическое лицо
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "individual")
public class Individual extends CommonEntity {

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
    protected boolean isRussianFederationCitizen = true;

    /**
     * Данные паспорта. Обязательный элемент
     */
    @OneToOne
    protected PassportDetails passportDetails;

    /**
     * Роли, в качестве которых может выступать физическое лицо
     */
    @ManyToMany
    protected Set<Role> roleSet = new HashSet<>();
}
