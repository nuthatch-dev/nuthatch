package ru.nuthatch.projectdocumentation.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;
import java.util.UUID;

/**
 * Описание комплексного типа RevisionInfo
 * информация о ревизии (изм.) проектной документации
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "revision_info")
public class RevisionInfo extends CommonEntity {
    /*
     Порядковый номер изменения
     */
    protected String revisionNumber;
    /*
     Дата выпуска изменения
     */
    protected Date revisionDate;
    /*
     Документ о внесении изменений
     */
    protected UUID approvingDocument;

}
