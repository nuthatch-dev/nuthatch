package ru.nuthatch.generalworkjournal.entity.representative;

import jakarta.persistence.Embeddable;
import jakarta.persistence.ManyToOne;
import lombok.Data;

/**
 * Описание комплексного типа: organizationWithOptionalSro
 * Организация (ЮЛ/ИП) с необязательным СРО
 */
@Data
@Embeddable
public class OrganizationWithOptionalSro {

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
