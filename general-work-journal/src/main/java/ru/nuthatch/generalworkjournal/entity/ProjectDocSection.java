package ru.nuthatch.generalworkjournal.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import ru.nuthatch.generalworkjournal.common.DocInfo;

import java.io.Serial;
import java.io.Serializable;
import java.util.UUID;

/**
 * Описание комплексного типа: projectDocSection
 * Раздел проектной документации (сокр.)
 */
@Data
@Entity
@Table(name = "project_doc_section")
public class ProjectDocSection implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * Идентификатор документа (наименование, номер)
     * Обязательный элемент
     */
    @Column(name = "doc_info",
            nullable = false)
    protected DocInfo docInfo;

    /**
     * Id раздела
     * Необязательный элемент
     * Строгий формат:
     * хххххххх-хххх-хххх-хххх-хххххххххххх
     * Наложенные ограничения
     * [0-9a-f]{8}-[0-9a-f]{4}-[0-5][0-9a-f]{3}-[089ab][0-9a-f]{3}-[0-9a-f]{12}
     */
    @Id
    @Column(name = "project_doc_section_uuid")
    protected UUID projectDocSectionUUID;
}
