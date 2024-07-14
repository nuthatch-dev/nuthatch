package ru.nuthatch.generalworkjournal.entity.controlevent;

import jakarta.persistence.*;
import lombok.Data;
import ru.nuthatch.generalworkjournal.common.DocInfo;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

/**
 * Раздел рабочей документации, требования которого нарушены
 */
@Data
@Entity
@Table(name = "working_doc_sections_set_item")
public class WorkingDocSectionsSetItem implements Serializable {

    @Id
    @GeneratedValue
    protected UUID uuid;

    /**
     * Идентификатор документа (наименование, номер)
     */
    @Embedded
    protected DocInfo docInfo;

    /**
     * Список номеров листов раздела рабочей документации,
     * требования которых нарушены
     */
    @ElementCollection
    protected Set<Integer> sheetsNumbersSet = new HashSet<>();
}
