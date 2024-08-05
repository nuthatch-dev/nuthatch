package ru.nuthatch.organization.entity;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Описание комплексного типа: PassportDetails.
 * Паспортные данные
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "passport_details")
public class PassportDetails extends CommonEntity {

    /**
     * Паспортные данные гражданина РФ
     */
    @Embedded
    protected PassportDetailsRussianFederation passportDetailsRussianFederation;

    /**
     * Документ подтверждающий личность иностранного гражданина
     */
    @Embedded
    protected DocumentDetailsForeign documentDetailsForeign;
}
