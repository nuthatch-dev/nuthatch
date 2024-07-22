package ru.nuthatch.generalworkjournal.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

/**
 * Описание комплексного типа: stateSupervisionInfo
 * Сведения о государственном строительном надзоре при строительстве, реконструкции,
 * капитальном ремонте объекта капитального строительства
 */
@Data
@Entity
@Table(name = "state_supervision_info")
public class StateSupervisionInfo implements Serializable {

    @Id
    @GeneratedValue
    protected UUID uuid;

    /**
     * Id записей сведений о государственном строительном надзоре
     * Обязательный элемент
     * Строгий формат:
     * хххххххх-хххх-хххх-хххх-хххххххххххх
     * Наложенные ограничения
     * [0-9a-f]{8}-[0-9a-f]{4}-[0-5][0-9a-f]{3}-[089ab][0-9a-f]{3}-[0-9a-f]{12}
     * Список
     */
    @ElementCollection
    protected Set<UUID> stateSupervisionRecordsIdsSet = new HashSet<>();
}
