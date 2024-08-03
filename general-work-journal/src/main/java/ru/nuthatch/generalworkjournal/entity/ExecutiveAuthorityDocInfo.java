package ru.nuthatch.generalworkjournal.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import ru.nuthatch.generalworkjournal.common.CommonEntity;
import ru.nuthatch.generalworkjournal.common.DocInfo;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

/**
 * Описание комплексного типа: ExecutiveAuthorityDocInfo
 * Сведения о документе, выданном органом власти
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "executive_authority_doc_info")
public class ExecutiveAuthorityDocInfo extends CommonEntity implements Serializable {

    /**
     * Реквизиты документа, со сроком действия и датой изменения
     * Обязательный элемент
     */
    @Embedded
    protected DocInfo docInfo;

    /**
     Срок действия документа
     Необязательный элемент
     Дата в формате <ГГГГ-ММ-ДД> (год-месяц-день)
     */
    protected Date expirationDate;

    /**
     Дата внесения изменений или исправлений
     Необязательный элемент
     Дата в формате <ГГГГ-ММ-ДД> (год-месяц-день)
     */
    protected Date docChangeDate;

    /**
     * ID органа (организации)
     * Необязательный элемент
     * Строгий формат:
     * хххххххх-хххх-хххх-хххх-хххххххххххх
     * Наложенные ограничения
     * [0-9a-f]{8}-[0-9a-f]{4}-[0-5][0-9a-f]{3}-[089ab][0-9a-f]{3}-[0-9a-f]{12}
     */
    @Column(name = "executive_authority_id")
    protected UUID executiveAuthorityId;

    /**
     * Наименование органа исполнительной власти или органа местного
     * самоуправления (организации), выдавшего документ
     * Обязательный элемент
     * Минимум 1 символ
     */
    @Column(name = "executive_authority_title",
            nullable = false)
    protected String executiveAuthorityTitle;
}
