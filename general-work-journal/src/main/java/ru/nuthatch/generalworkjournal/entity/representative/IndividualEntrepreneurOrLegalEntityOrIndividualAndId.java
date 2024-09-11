package ru.nuthatch.generalworkjournal.entity.representative;

import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

/**
 * Описание комплексного типа: IndividualEntrepreneurOrLegalEntityOrIndividualAndId
 * ИП / ЮЛ / ФЛ (с указанием ID)
 */
@Data
@Entity
@Table(name = "entrepreneur_or_legal_or_individual")
public class IndividualEntrepreneurOrLegalEntityOrIndividualAndId {

    @Id
    @GeneratedValue
    protected UUID uuid;

    /*
    Обязательный элемент (одно значение из элементов типа)
    Организация (ИП / ЮЛ) с необязательным СРО
     */
    @OneToOne(cascade = CascadeType.ALL)
    protected OrganizationWithOptionalSro organization;

    /*
    Информация о физ. лице
     */
    @ManyToOne
    protected Individual individual;
}
