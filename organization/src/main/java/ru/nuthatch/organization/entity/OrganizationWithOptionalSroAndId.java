package ru.nuthatch.organization.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.UUID;

/**
 * Описание комплексного типа: organizationWithOptionalSroAndId
 * Организация (ЮЛ/ИП) с необязательным СРО и ID организации
 */
@Data
@Entity
@Table(name = "organization_with_optional_sro_and_id")
public class OrganizationWithOptionalSroAndId implements Serializable {
    @Id
    @GeneratedValue
    protected UUID uuid;

    /*
    Организация (ЮЛ/ИП) с необязательным СРО
     */
    @Embedded
    protected OrganizationWithOptionalSro organizationInfo;

}
