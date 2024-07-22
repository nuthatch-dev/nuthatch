package ru.nuthatch.generalworkjournal.entity.controlevent;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import ru.nuthatch.generalworkjournal.common.CommonEntity;
import ru.nuthatch.generalworkjournal.common.DocInfo;

import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "technical_regulations_section")
public class TechnicalRegulationsSection extends CommonEntity implements Serializable {

    /**
     * Структурная единица технического регламента, иного нормативно-правового акта
     * Обязательный элемент
     */
    @Embedded
    protected DocInfo docInfo;

    /**
     * Отношение к TechnicalRegulations
     */
    @ManyToOne
    protected TechnicalRegulations technicalRegulationDetail;
}
