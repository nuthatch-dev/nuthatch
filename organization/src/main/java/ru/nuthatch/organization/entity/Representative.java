package ru.nuthatch.organization.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import ru.nuthatch.baseentity.entity.BaseRepresentative;

/**
 * Описание комплексного типа: Representative.
 * Представитель, имеющий ФИО, должность
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "representative")
public class Representative extends BaseRepresentative implements BaseEntity {

    /*
    Организация (ЮЛ/ИП) с необязательным СРО
     */
    @ManyToOne
    protected OrganizationWithOptionalSroAndId organization;

}
