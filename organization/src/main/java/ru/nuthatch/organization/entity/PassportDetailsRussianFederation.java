package ru.nuthatch.organization.entity;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.AttributeOverrides;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;

import java.util.Date;

@Data
@Embeddable
@AttributeOverrides({
        @AttributeOverride(name = "series", column = @Column(
                name = "passport_details_russian_federation_series")),
        @AttributeOverride(name = "number", column = @Column(
                name = "passport_details_russian_federation_number")),
        @AttributeOverride(name = "date_issue", column = @Column(
                name = "passport_details_russian_federation_date_issue"))
})
public class PassportDetailsRussianFederation {

    protected String series;
    protected String number;
    protected Date dateIssue;

}
