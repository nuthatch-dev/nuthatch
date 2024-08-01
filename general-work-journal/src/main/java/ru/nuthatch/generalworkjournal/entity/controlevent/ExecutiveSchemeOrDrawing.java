package ru.nuthatch.generalworkjournal.entity.controlevent;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import ru.nuthatch.generalworkjournal.common.CommonEntity;
import ru.nuthatch.generalworkjournal.common.DocInfo;

import java.io.Serializable;
import java.util.Date;

/**
 * Исполнительная схема или чертёж
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "executive_scheme_or_drawing")
public class ExecutiveSchemeOrDrawing extends CommonEntity implements Serializable {

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
