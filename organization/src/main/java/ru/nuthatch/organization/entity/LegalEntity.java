package ru.nuthatch.organization.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import ru.nuthatch.baseentity.entity.BaseLegalEntity;

/**
 * Описание комплексного типа: LegalEntity.
 * Юридическое лицо
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "legal_entity")
public class LegalEntity extends BaseLegalEntity implements BaseEntity {

    /**
     * Информация о саморегулируемой организации
     */
    @ManyToOne
    protected Sro sro;

}
