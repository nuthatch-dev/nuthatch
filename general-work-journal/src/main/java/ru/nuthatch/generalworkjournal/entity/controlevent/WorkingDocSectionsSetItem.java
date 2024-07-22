package ru.nuthatch.generalworkjournal.entity.controlevent;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import ru.nuthatch.generalworkjournal.common.CommonEntity;
import ru.nuthatch.generalworkjournal.common.DocInfo;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * Раздел рабочей документации, требования которого нарушены
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "working_doc_sections_set_item")
public class WorkingDocSectionsSetItem extends CommonEntity implements Serializable {

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
