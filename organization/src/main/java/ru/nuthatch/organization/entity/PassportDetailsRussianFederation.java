package ru.nuthatch.organization.entity;

import jakarta.persistence.Embeddable;
import lombok.Data;

import java.util.Date;

@Data
@Embeddable
public class PassportDetailsRussianFederation {

    protected String series;
    protected String number;
    protected Date dateIssue;

}
