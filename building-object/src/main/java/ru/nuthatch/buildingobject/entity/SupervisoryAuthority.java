package ru.nuthatch.buildingobject.entity;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.AttributeOverrides;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;

import java.util.UUID;

/**
 * Описание комплексного типа: SupervisoryAuthority
 * Орган государственного строительного надзора
 */
@Data
@Embeddable
@AttributeOverrides({
        @AttributeOverride(name = "supervisory_authority_name",
                column = @Column(name = "supervisory_authority_supervisory_authority_name")),
        @AttributeOverride(name = "supervisory_authority_postal_address",
                column = @Column(name = "supervisory_authority_supervisory_authority_postal_address")),
        @AttributeOverride(name = "phone",
                column = @Column(name = "supervisory_authority_phone"))
})
public class SupervisoryAuthority {

    /**
     * Наименование контрольного (надзорного) органа
     * Обязательный элемент
     * Минимум 1 символ
     */
    protected String supervisoryAuthorityName;

    /**
     * Почтовый адрес
     * Обязательный элемент
     */
    protected UUID supervisoryAuthorityPostalAddress;

    /**
     * Телефон/Факс
     * Необязательный элемент
     * Минимум 1 символ
     */
    protected String phone;
}
