package ru.nuthatch.baseentity.entity;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;
import lombok.Data;

/**
 * Описание комплексного типа: PassportDetails.
 * Паспортные данные
 */
@Data
@Embeddable
public class PassportDetails {

    /**
     * Паспортные данные гражданина РФ
     */
    @Embedded
    @AttributeOverride(name = "series", column = @Column(name = "ru_series"))
    @AttributeOverride(name = "number", column = @Column(name = "ru_number"))
    @AttributeOverride(name = "dateIssue", column = @Column(name = "ru_date_issue"))
    protected PassportDetailsRussianFederation passportDetailsRussianFederation;

    /**
     * Документ подтверждающий личность иностранного гражданина
     */
    @Embedded
    @AttributeOverride(name = "docName", column = @Column(name = "foreign_doc_name"))
    @AttributeOverride(name = "series", column = @Column(name = "foreign_series"))
    @AttributeOverride(name = "number", column = @Column(name = "foreign_number"))
    @AttributeOverride(name = "dateIssue", column = @Column(name = "foreign_date_issue"))
    protected DocumentDetailsForeign documentDetailsForeign;
}
