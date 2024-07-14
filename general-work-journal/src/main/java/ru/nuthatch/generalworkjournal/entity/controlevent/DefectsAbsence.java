package ru.nuthatch.generalworkjournal.entity.controlevent;

import jakarta.persistence.*;
import lombok.Data;
import ru.nuthatch.generalworkjournal.common.Representative;

import java.io.Serializable;
import java.util.UUID;

/**
 * Сведения об отсутствии недостатков
 */
@Data
@Entity
@Table(name = "defects_absence")
public class DefectsAbsence implements Serializable {

    @Id
    @GeneratedValue
    protected UUID uuid;

    /**
     * Сведения об отсутствии недостатков
     */
    @Column(name = "defects_absence_record",
            nullable = false)
    protected String defectsAbsenceRecord;

    /**
     * Сведения о сотруднике, проводившем контроль
     */
    @ManyToOne
    protected Representative defectsAbsenceInspectionEmployee;
}
