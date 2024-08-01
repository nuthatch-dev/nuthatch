package ru.nuthatch.generalworkjournal.entity.NTD;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import ru.nuthatch.generalworkjournal.common.CommonEntity;
import ru.nuthatch.generalworkjournal.common.DocInfo;

import java.io.Serializable;

/**
 * Описание комплексного типа: TechnicalRegulationUnit
 * Структурная единица технического регламента, иного нормативно-правового акта
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "technical_regulation_unit")
public class TechnicalRegulationUnit extends CommonEntity implements Serializable {

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
