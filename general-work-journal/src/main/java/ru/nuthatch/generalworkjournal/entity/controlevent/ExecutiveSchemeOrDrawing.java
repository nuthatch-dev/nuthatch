package ru.nuthatch.generalworkjournal.entity.controlevent;

import jakarta.persistence.*;
import lombok.Data;
import ru.nuthatch.generalworkjournal.common.DocInfo;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

/**
 * Исполнительная схема или чертёж
 */
@Data
@Entity
@Table(name = "executive_scheme_or_drawing")
public class ExecutiveSchemeOrDrawing implements Serializable {

    /**
     * Id документа
     */
    @Id
    @GeneratedValue
    protected UUID docId;

    /**
     * Наименование и номер документа
     */
    @Embedded
    protected DocInfo docInfo;

    /**
     * Дата
     */
    @Column(nullable = false)
    protected Date date;
}
