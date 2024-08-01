package ru.nuthatch.generalworkjournal.entity.controlevent;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import ru.nuthatch.generalworkjournal.common.CommonEntity;
import ru.nuthatch.generalworkjournal.common.DateTimeInterval;

import java.io.Serializable;

/**
 * Описание комплексного типа: controlEventRecord
 * Сведения о контрольной процедуре с указанием недостатков,
 * выявленных в ходе её проведения
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "control_event_record")
public class ControlEventRecord extends CommonEntity implements Serializable {

    /**
     * Отношение к ControlEventInfo
     */
    @ManyToOne
    protected ControlEventInfo controlEventInfo;

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
