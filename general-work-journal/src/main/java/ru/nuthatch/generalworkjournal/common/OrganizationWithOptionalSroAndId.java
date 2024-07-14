package ru.nuthatch.generalworkjournal.common;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.UUID;

/**
 * Описание комплексного типа: organizationWithOptionalSroAndId
 * Организация (ЮЛ/ИП) с необязательным СРО и ID организации
 */
@Data
@Entity
@Table(name = "organization_with_optional_sro_and_id")
public class OrganizationWithOptionalSroAndId implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * ID участника
     * Обязательный элемент
     * Строгий формат:
     * хххххххх-хххх-хххх-хххх-хххххххххххх
     * Наложенные ограничения
     * [0-9a-f]{8}-[0-9a-f]{4}-[0-5][0-9a-f]{3}-[089ab][0-9a-f]{3}-[0-9a-f]{12}
     */
    @Id
    @GeneratedValue
    @Column(name = "participant_id",
            nullable = false)
    protected UUID participantId;

    /**
     * Информация о юридическом лице
     * Обязательный элемент (одно значение из элементов типа)
     */
    @Column(name = "legal_entity_uuid")
    protected UUID legalEntity;

    /**
     * Информация об индивидуальном предпринимателе
     */
    @Column(name = "individual_entrepreneur_uuid")
    protected UUID individualEntrepreneur;

    /**
     * Информация о саморегулируемой организации
     * Необязательный элемент
     */
    @Column(name = "sro")
    protected UUID sro;

}
