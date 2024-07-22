package ru.nuthatch.generalworkjournal.entity.controlevent;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import ru.nuthatch.generalworkjournal.common.CommonEntity;
import ru.nuthatch.generalworkjournal.common.DocInfo;

import java.io.Serializable;

/**
 * Раздел проектной документации, требования которого нарушены
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "project_doc_sections_set_item")
public class ProjectDocSectionsSetItem extends CommonEntity implements Serializable {

    /**
     * Идентификатор документа (наименование, номер)
     */
    @Embedded
    protected DocInfo docInfo;

}
