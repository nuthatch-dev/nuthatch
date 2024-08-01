package ru.nuthatch.generalworkjournal.common;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.UUID;

/**
 * Описание комплексного типа: organizationWithOptionalSroAndId
 * Организация (ЮЛ/ИП) с необязательным СРО и ID организации
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "organization_with_optional_sro_and_id")
public class OrganizationWithOptionalSroAndId extends CommonEntity implements Serializable {

    /**
     * Информация о юридическом лице
     * Обязательный элемент (одно значение из элементов типа)
     */
    @Column(name = "legal_entity_uuid")
    protected UUID legalEntity;

    /**
     * Информация об индивидуальном предпринимателе
     */
    @Column(name = "individual_entrepreneur_uuid")
    protected UUID individualEntrepreneur;

    /**
     * Информация о саморегулируемой организации
     * Необязательный элемент
     */
    @Column(name = "sro")
    protected UUID sro;

}
