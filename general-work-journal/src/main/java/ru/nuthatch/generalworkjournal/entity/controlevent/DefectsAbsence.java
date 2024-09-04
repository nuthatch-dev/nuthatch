package ru.nuthatch.generalworkjournal.entity.controlevent;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import ru.nuthatch.generalworkjournal.common.CommonEntity;
import ru.nuthatch.generalworkjournal.entity.representative.Representative;

import java.io.Serializable;

/**
 * Сведения об отсутствии недостатков
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "defects_absence")
public class DefectsAbsence extends CommonEntity implements Serializable {

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
