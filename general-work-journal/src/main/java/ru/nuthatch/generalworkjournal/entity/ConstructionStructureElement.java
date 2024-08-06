package ru.nuthatch.generalworkjournal.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;

import java.util.UUID;

/**
 * Описание комплексного типа: constructionStructureElement
 * Структурный элемент здания, строения или сооружения
 */
@Data
@Embeddable
public class ConstructionStructureElement {

    /**
     * Id структурного элемента
     * Необязательный элемент
     * Строгий формат:
     * хххххххх-хххх-хххх-хххх-хххххххххххх
     * Наложенные ограничения
     * [0-9a-f]{8}-[0-9a-f]{4}-[0-5][0-9a-f]{3}-[089ab][0-9a-f]{3}-[0-9a-f]{12}
     */
    @Column(name = "structure_element_id")
    protected UUID structureElementId;

    /**
     * Наименование структурного элемента здания, строения или сооружения
     * Обязательный элемент
     * Минимум 1 символ
     */
    @Column(name = "structure_element_name")
    protected String structureElementName;
}
