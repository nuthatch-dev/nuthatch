package ru.nuthatch.generalworkjournal.entity.controlevent;

import jakarta.persistence.*;
import lombok.Data;
import ru.nuthatch.generalworkjournal.common.DateTimeInterval;

import java.io.Serializable;
import java.util.UUID;

/**
 * Описание комплексного типа: controlEventRecord
 * Сведения о контрольной процедуре с указанием недостатков,
 * выявленных в ходе её проведения
 */
@Data
@Entity
@Table(name = "control_event_record")
public class ControlEventRecord implements Serializable {

    /**
     * Отношение к ControlEventInfo
     */
    @ManyToOne
    protected ControlEventInfo controlEventInfo;

    /**
     * id записи о выполнении контрольного мероприятия
     * Обязательный элемент
     * Строгий формат:
     * хххххххх-хххх-хххх-хххх-хххххххххххх
     * Наложенные ограничения
     * [0-9a-f]{8}-[0-9a-f]{4}-[0-5][0-9a-f]{3}-[089ab][0-9a-f]{3}-[0-9a-f]{12}
     */
    @Id
    @GeneratedValue
    @Column(name = "control_event_record_id")
    protected UUID controlEventRecordId;

    /**
     * Сроки проведения контрольного мероприятия
     * Обязательный элемент
     */
    @Embedded
    protected DateTimeInterval controlEventPeriod;

    /**
     * Недостаток предмета освидетельствования
     */
    @OneToOne
    protected IdentifiedDefect identifiedDefect;

    /**
     * Сведения об отсутствии недостатков
     */
    @OneToOne
    protected DefectsAbsence defectsAbsence;
}
