package ru.nuthatch.generalworkjournal.entity.controlevent;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import ru.nuthatch.generalworkjournal.common.CommonEntity;
import ru.nuthatch.generalworkjournal.common.ExtraParameter;
import ru.nuthatch.generalworkjournal.entity.GeneralWorkJournal;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * Сведения о результатах контрольного мероприятия
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "control_event_info")
public class ControlEventInfo extends CommonEntity implements Serializable {

    /**
     * Отношение к GeneralWorkJournal
     */
    @ManyToOne
    protected GeneralWorkJournal generalWorkJournal;

    /*
    Список результатов контрольного мероприятия
    TODO: controlEventResultsList (native query)
     */

    /**
     * Дополнительные параметры
     */
    @ManyToMany
    @JoinTable(name = "event_extra")
    protected Set<ExtraParameter> extraParameterSet = new HashSet<>();

}
