package ru.nuthatch.generalworkjournal.entity.controlevent;

import jakarta.persistence.*;
import lombok.Data;
import ru.nuthatch.generalworkjournal.common.DocInfo;

import java.io.Serializable;
import java.util.UUID;

// TODO: Повторение нормативно-технической документации (вынести в отдельный сервис)

@Data
@Entity
@Table(name = "technical_regulation")
public class TechnicalRegulations implements Serializable {

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
     * TODO: Список technicalRegulationsSection (native query)
     */
}
