package ru.nuthatch.generalworkjournal.entity.controlevent;

import jakarta.persistence.*;
import lombok.Data;
import ru.nuthatch.generalworkjournal.entity.GeneralWorkJournal;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

/**
 * Сведения о результатах контрольного мероприятия
 */
@Data
@Entity
@Table(name = "control_event_info")
public class ControlEventInfo implements Serializable {

    @Id
    @GeneratedValue
    protected UUID uuid;

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
    protected Set<ExtraParameter> extraParameterSet = new HashSet<>();

}
