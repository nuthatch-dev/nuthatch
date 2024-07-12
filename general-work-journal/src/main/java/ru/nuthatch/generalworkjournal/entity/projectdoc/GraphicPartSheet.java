package ru.nuthatch.generalworkjournal.entity.projectdoc;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.util.UUID;

/**
 * Описание комплексного типа: GraphicPartSheet
 * Лист внутри раздела
 */
@Data
@Entity
@Table(name = "graphic_part_sheet")
public class GraphicPartSheet implements Serializable {

    @Id
    @GeneratedValue
    protected UUID uuid;

    /**
     * Номер листа внутри раздела
     * Обязательный элемент
     */
    @Column(name = "sheet_number",
            nullable = false)
    protected String sheetNumber;

    /**
     * Наименование листа внутри раздела
     * Обязательный элемент
     */
    @Column(name = "sheet_name",
            nullable = false)
    protected String sheetName;

    /**
     * Id файла чертежа (внутри раздела)
     * Необязательный элемент
     * Строгий формат:
     * хххххххх-хххх-хххх-хххх-хххххххххххх
     * Наложенные ограничения
     * [0-9a-f]{8}-[0-9a-f]{4}-[0-5][0-9a-f]{3}-[089ab][0-9a-f]{3}-[0-9a-f]{12}
     */
    @Column(name = "sheet_id")
    protected UUID sheetId;

    /**
     * Отношение к разделу документации
     */
    @ManyToOne
    protected ProjectDocumentationSection projectDocumentationSection;
}
