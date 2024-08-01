package ru.nuthatch.generalworkjournal.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import ru.nuthatch.generalworkjournal.common.CommonEntity;

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
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "state_supervision_info")
public class StateSupervisionInfo extends CommonEntity implements Serializable {

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
