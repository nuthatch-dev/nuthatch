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
        @AttributeOverride(name = "doc_name", column = @Column(name = "document_details_foreign_doc_name")),
        @AttributeOverride(name = "series", column = @Column(name = "document_details_foreign_series")),
        @AttributeOverride(name = "number", column = @Column(name = "document_details_foreign_number")),
        @AttributeOverride(name = "date_issue", column = @Column(name = "document_details_foreign_date_issue"))
})
public class DocumentDetailsForeign {

    protected String docName;
    protected String series;
    protected String number;
    protected Date dateIssue;

}
