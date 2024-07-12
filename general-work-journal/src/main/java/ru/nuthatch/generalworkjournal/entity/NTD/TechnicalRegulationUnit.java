package ru.nuthatch.generalworkjournal.entity.NTD;

import jakarta.persistence.*;
import lombok.Data;
import ru.nuthatch.generalworkjournal.common.DocInfo;

import java.io.Serializable;
import java.util.UUID;

/**
 * Описание комплексного типа: TechnicalRegulationUnit
 * Структурная единица технического регламента, иного нормативно-правового акта
 */
@Data
@Entity
@Table(name = "technical_regulation_unit")
public class TechnicalRegulationUnit implements Serializable {

    @Id
    @GeneratedValue
    protected UUID uuid;

    /**
     * Структурная единица технического регламента, иного нормативно-правового акта
     * Обязательный элемент
     */
    @Embedded
    protected DocInfo docInfo;

    /**
     * Отношение к TechnicalRegulationDetail
     */
    @ManyToOne
    protected TechnicalRegulationDetail technicalRegulationDetail;
}
