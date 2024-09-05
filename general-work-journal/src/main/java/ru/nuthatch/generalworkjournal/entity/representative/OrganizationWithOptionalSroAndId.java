package ru.nuthatch.generalworkjournal.entity.representative;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import ru.nuthatch.generalworkjournal.common.CommonEntity;

import java.io.Serializable;

/**
 * Описание комплексного типа: organizationWithOptionalSroAndId
 * Организация (ЮЛ/ИП) с необязательным СРО и ID организации
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "organization_with_optional_sro_and_id")
public class OrganizationWithOptionalSroAndId extends CommonEntity implements Serializable {

    /*
    Организация (ЮЛ/ИП) с необязательным СРО
     */
    @Embedded
    protected OrganizationWithOptionalSro organizationInfo;

}
