package ru.nuthatch.buildingobject.entity;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.AttributeOverrides;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;

import java.util.UUID;

/**
 * Описание комплексного типа: ExecutiveAuthorityDocInfo
 * Сведения о документе, выданном органом власти
 */
@Data
@Embeddable
@AttributeOverrides({
        @AttributeOverride(name = "doc_details",
                column = @Column(name = "executive_authority_doc_info_doc_details")),
        @AttributeOverride(name = "executive_authority_id",
                column = @Column(name = "executive_authority_doc_info_executive_authority_id")),
        @AttributeOverride(name = "executive_authority_title",
                column = @Column(name = "executive_authority_doc_info_executive_authority_title")),
})
public class ExecutiveAuthorityDocInfo {

    /**
     * Реквизиты документа, со сроком действия и датой изменения
     * Обязательный элемент
     */
    protected UUID docDetails;

    /**
     * ID органа (организации)
     * Необязательный элемент
     * Строгий формат:
     * хххххххх-хххх-хххх-хххх-хххххххххххх
     * Наложенные ограничения
     * [0-9a-f]{8}-[0-9a-f]{4}-[0-5][0-9a-f]{3}-[089ab][0-9a-f]{3}-[0-9a-f]{12}
     */
    protected UUID executiveAuthorityId;

    /**
     * Наименование органа исполнительной власти или органа местного
     * самоуправления (организации), выдавшего документ
     * Обязательный элемент
     * Минимум 1 символ
     */
    protected String executiveAuthorityTitle;
}
