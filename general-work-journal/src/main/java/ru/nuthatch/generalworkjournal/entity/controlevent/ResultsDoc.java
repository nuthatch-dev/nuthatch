package ru.nuthatch.generalworkjournal.entity.controlevent;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import ru.nuthatch.generalworkjournal.common.CommonEntity;
import ru.nuthatch.generalworkjournal.common.DocInfo;

import java.io.Serializable;
import java.util.Date;

/**
 * Документ с результатами экспертиз, обследований, лабораторных и
 * иных испытаний выполненных работ
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "results_doc")
public class ResultsDoc extends CommonEntity implements Serializable {

    /**
     * Наименование и номер (код/шифр) документа
     */
    @Embedded
    protected DocInfo docInfo;

    /**
     * Дата
     */
    @Column(nullable = false)
    protected Date date;

    /**
     * Наименование экспертизы, обследования, испытания и т.п.
     */
    @Column(name = "testing_name",
            nullable = false)
    protected String testingName;

}
