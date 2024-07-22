package ru.nuthatch.generalworkjournal.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

/**
 * Описание комплексного типа: generalWorkJournalCommonInfo
 * Подписываемая информация об общем журнале работ
 */

@Data
@Entity
@Table(name = "general_work_journal_common_info")
public class GeneralWorkJournalCommonInfo implements Serializable {

    /**
     * ID подписываемой части (информация об общем журнале работ)
     * Обязательный элемент
     * Строгий формат:
     * _хххххххх-хххх-хххх-хххх-хххххххххххх
     * Наложенные ограничения
     * _[0-9a-f]{8}-[0-9a-f]{4}-[0-5][0-9a-f]{3}-[089ab][0-9a-f]{3}-[0-9a-f]{12}
     */
    @Id
    @GeneratedValue
    protected UUID uuid;

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
