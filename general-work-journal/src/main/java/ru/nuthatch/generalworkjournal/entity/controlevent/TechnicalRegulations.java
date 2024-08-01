package ru.nuthatch.generalworkjournal.entity.controlevent;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import ru.nuthatch.generalworkjournal.common.CommonEntity;
import ru.nuthatch.generalworkjournal.common.DocInfo;

import java.io.Serializable;

// TODO: Повторение нормативно-технической документации (вынести в отдельный сервис)

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "technical_regulation")
public class TechnicalRegulations extends CommonEntity implements Serializable {

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
