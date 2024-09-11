package ru.nuthatch.generalworkjournal.entity.representative;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.util.UUID;

/**
 * Описание комплексного типа: organizationWithOptionalSro
 * Организация (ЮЛ/ИП) с необязательным СРО
 */
@Data
@Entity
@Table(name = "organization_with_optional_sro")
public class OrganizationWithOptionalSro implements Serializable {

    @Id
    @GeneratedValue
    protected UUID uuid;

    /*
    Обязательный элемент (одно значение из элементов типа)
    Информация о юридическом лице
     */
    @ManyToOne
    protected LegalEntity legalEntity;

    /*
    Информация об индивидуальном предпринимателе
     */
    @ManyToOne
    protected IndividualEntrepreneur individualEntrepreneur;
}
