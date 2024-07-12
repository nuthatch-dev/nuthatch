package ru.nuthatch.generalworkjournal.entity.projectdoc;

import jakarta.persistence.*;
import lombok.Data;
import ru.nuthatch.generalworkjournal.entity.WorksPerformingInfo;

import java.io.Serializable;
import java.util.UUID;

/**
 * Описание комплексного типа: ProjectDocumentationSection
 * Раздел проектной документации
 */
@Data
@Entity
@Table(name = "project_documentation_section")
public class ProjectDocumentationSection implements Serializable {

    @Id
    @GeneratedValue
    protected UUID uuid;

    /**
     * Шифр раздела проектной документации
     * Обязательный элемент
     * Минимум 1 символ
     */
    @Column(name = "project_documentation_section_code",
            nullable = false)
    protected String projectDocumentationSectionCode;

    /**
     * Наименование раздела проектной документации
     * Обязательный элемент
     * Минимум 1 символ
     */
    @Column(name = "project_documentation_section_name",
            nullable = false)
    protected String projectDocumentationSectionName;

    /*
    Список листов внутри раздела
    TODO: Список graphicPartSheet (native query)
     */

    /**
     * Id файла чертежа
     * Необязательный элемент
     * Строгий формат:
     * хххххххх-хххх-хххх-хххх-хххххххххххх
     * Наложенные ограничения
     * [0-9a-f]{8}-[0-9a-f]{4}-[0-5][0-9a-f]{3}-[089ab][0-9a-f]{3}-[0-9a-f]{12}
     */
    @Column(name = "project_documentation_id")
    protected UUID projectDocumentationId;

    /**
     * Отношение к WorksPerformingInfo
     */
    @ManyToOne
    protected WorksPerformingInfo worksPerformingInfo;

}
