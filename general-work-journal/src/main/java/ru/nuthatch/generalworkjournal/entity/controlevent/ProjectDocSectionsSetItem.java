package ru.nuthatch.generalworkjournal.entity.controlevent;

import jakarta.persistence.*;
import lombok.Data;
import ru.nuthatch.generalworkjournal.common.DocInfo;

import java.io.Serializable;
import java.util.UUID;

/**
 * Раздел проектной документации, требования которого нарушены
 */
@Data
@Entity
@Table(name = "project_doc_sections_set_item")
public class ProjectDocSectionsSetItem implements Serializable {

    @Id
    @GeneratedValue
    protected UUID uuid;

    /**
     * Идентификатор документа (наименование, номер)
     */
    @Embedded
    protected DocInfo docInfo;

}
