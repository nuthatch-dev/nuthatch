package ru.nuthatch.baseentity.entity;

import jakarta.persistence.Embeddable;
import lombok.Data;

import java.util.Date;

@Data
@Embeddable
public class DocumentDetailsForeign {

    protected String docName;
    protected String series;
    protected String number;
    protected Date dateIssue;

}
