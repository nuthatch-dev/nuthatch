package ru.nuthatch.generalworkjournal.common;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.util.UUID;

/**
 * Информация о представителе (ФИО, должность, ID сотрудника)
 */
@Data
@Entity
@Table(name = "representative")
public class Representative implements Serializable {

    /**
     * id сотрудника
     */
    @Id
    @GeneratedValue
    protected UUID personId;

    @Embedded
    protected FullNameGroup fullNameGroup;

    /**
     * Должность
     */
    @Column(nullable = false)
    protected String position;
}
