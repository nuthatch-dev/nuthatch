package ru.nuthatch.generalworkjournal.entity;

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
public class PostalOrConstructionSiteAddress {

    /**
     * Информация о почтовом адресе
     * Обязательный элемент, если не заполнен строительный адрес
     */
    @Column(name = "postal_address")
    protected UUID postalAddress;

    /**
     * Информация о строительном адресе
     * Обязательный элемент, если не заполнен почтовый адрес
     */
    @Column(name = "construction_site_address")
    protected UUID constructionSiteAddress;
}
