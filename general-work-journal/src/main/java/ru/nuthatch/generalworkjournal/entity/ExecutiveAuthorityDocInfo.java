package ru.nuthatch.generalworkjournal.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.UUID;

/**
 * Описание комплексного типа: ExecutiveAuthorityDocInfo
 * Сведения о документе, выданном органом власти
 */
@Data
@Entity
@Table(name = "executive_authority_doc_info")
public class ExecutiveAuthorityDocInfo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * Реквизиты документа, со сроком действия и датой изменения
     * Обязательный элемент
     */
    @Id
    @Column(name = "doc_details")
    protected UUID docDetails;

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
