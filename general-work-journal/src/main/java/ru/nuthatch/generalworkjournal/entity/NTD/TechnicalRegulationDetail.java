package ru.nuthatch.generalworkjournal.entity.NTD;

import jakarta.persistence.*;
import lombok.Data;
import ru.nuthatch.generalworkjournal.common.DocInfo;
import ru.nuthatch.generalworkjournal.entity.WorksPerformingInfo;

import java.io.Serializable;
import java.util.UUID;

/**
 * Описание комплексного типа: TechnicalRegulationDetail
 * Реквизиты технического регламента/иного нормативно-правового акта
 */
@Data
@Entity
@Table(name = "technical_regulation_detail")
public class TechnicalRegulationDetail implements Serializable {

    @Id
    @GeneratedValue
    protected UUID uuid;

    /**
     * Идентификатор документа (наименование, номер)
     * Обязательный элемент
     */
    @Embedded
    protected DocInfo docInfo;

    /*
     * Реквизиты структурных единиц технических регламентов,
     * иных нормативно-правовых актов
     * Необязательный элемент
     * TODO: Список technicalRegulationUnit (native query)
     */

    /**
     * Отношение к WorksPerformingInfo
     */
    @ManyToOne
    protected WorksPerformingInfo worksPerformingInfo;
}
