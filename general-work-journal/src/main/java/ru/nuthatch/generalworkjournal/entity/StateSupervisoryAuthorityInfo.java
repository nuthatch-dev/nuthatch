package ru.nuthatch.generalworkjournal.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.util.UUID;

/**
 * Описание комплексного типа: StateSupervisoryAuthorityInfo
 * Сведения о государственном строительном надзоре
 */
@Data
@Entity
@Table(name = "state_supervisory_authority_info")
public class StateSupervisoryAuthorityInfo implements Serializable {

    @Id
    @GeneratedValue
    protected UUID uuid;

    /**
     * Орган государственного строительного надзора
     * Обязательный элемент
     */
    // TODO: legalEntityWithId
    @Column(name = "supervisory_authority",
            nullable = false)
    protected UUID supervisoryAuthority;

    /**
     * Должностное лицо органа государственного строительного надзора
     * Обязательный элемент
     */
    @ManyToOne
    protected ExecutiveGovernmentAgencyRepresentative supervisoryAuthorityOfficialPerson;
}
