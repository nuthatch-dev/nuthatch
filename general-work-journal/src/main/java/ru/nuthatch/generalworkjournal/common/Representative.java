package ru.nuthatch.generalworkjournal.common;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * Информация о представителе (ФИО, должность, ID сотрудника)
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "representative")
public class Representative extends CommonEntity implements Serializable {

    @Embedded
    protected FullNameGroup fullNameGroup;

    /**
     * Должность
     */
    @Column(nullable = false)
    protected String position;
}
