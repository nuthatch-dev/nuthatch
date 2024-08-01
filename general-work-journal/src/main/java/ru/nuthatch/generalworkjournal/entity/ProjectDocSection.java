package ru.nuthatch.generalworkjournal.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import ru.nuthatch.generalworkjournal.common.CommonEntity;
import ru.nuthatch.generalworkjournal.common.DocInfo;

import java.io.Serializable;

/**
 * Описание комплексного типа: projectDocSection
 * Раздел проектной документации (сокр.)
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "project_doc_section")
public class ProjectDocSection extends CommonEntity implements Serializable {

    /**
     * Идентификатор документа (наименование, номер)
     * Обязательный элемент
     */
    @Column(name = "doc_info",
            nullable = false)
    protected DocInfo docInfo;

}
