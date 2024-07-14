package ru.nuthatch.generalworkjournal.entity;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.AttributeOverrides;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;

import java.util.UUID;

/**
 * Описание комплексного типа: PostalOrConstructionSiteAddress
 * Тип, содержащий в себе информацию об адресе объекта (Почтовом или строительном)
 */
@Data
@Embeddable
@AttributeOverrides({
        @AttributeOverride(name = "postal_address",
                column = @Column(name = "postal_or_construction_site_address_postal_address")),
        @AttributeOverride(name = "construction_site_address",
                column = @Column(name = "postal_or_construction_site_address_construction_site_address"))
})
public class PostalOrConstructionSiteAddress {

    /**
     * Информация о почтовом адресе
     * Обязательный элемент, если не заполнен строительный адрес
     */
    protected UUID postalAddress;

    /**
     * Информация о строительном адресе
     * Обязательный элемент, если не заполнен почтовый адрес
     */
    protected UUID constructionSiteAddress;
}
