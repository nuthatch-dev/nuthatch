package ru.nuthatch.generalworkjournal.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import ru.nuthatch.generalworkjournal.common.CommonEntity;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

/**
 * Описание комплексного типа: generalWorkJournalCommonInfo
 * Подписываемая информация об общем журнале работ
 */

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "general_work_journal_common_info")
public class GeneralWorkJournalCommonInfo extends CommonEntity implements Serializable {

    /**
     * Объём журнала
     * Обязательный элемент
     */
    @Embedded
    @Column(name = "journal_volume",
            nullable = false)
    protected JournalVolume journalVolume;

    /**
     * Сведения о временном периоде строительства, содержащемся в журнале
     * Обязательный элемент
     */
    @Column(name = "begin_date",
            nullable = false)
    protected Date beginDate;

    @Column(name = "end_date",
            nullable = false)
    protected Date endDate;

    /**
     * Сведения о представителе застройщика, внёсшем общие сведения об общем журнале работ
     * Обязательный элемент
     */
    @Column(name = "developer_representative",
            nullable = false)
    protected UUID developerRepresentative;
}
