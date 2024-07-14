package ru.nuthatch.generalworkjournal.entity.controlevent;

import jakarta.persistence.*;
import lombok.Data;
import ru.nuthatch.generalworkjournal.common.DocInfo;

import java.io.Serializable;
import java.util.UUID;

@Data
@Entity
@Table(name = "technical_regulations_section")
public class TechnicalRegulationsSection implements Serializable {

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
     * Отношение к TechnicalRegulations
     */
    @ManyToOne
    protected TechnicalRegulations technicalRegulationDetail;
}
