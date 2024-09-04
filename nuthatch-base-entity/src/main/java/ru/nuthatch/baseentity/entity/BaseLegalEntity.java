package ru.nuthatch.baseentity.entity;

import jakarta.persistence.*;
import lombok.Data;
import ru.nuthatch.baseentity.Role;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

/**
 * Описание комплексного типа: LegalEntity.
 * Юридическое лицо
 */
@Data
@MappedSuperclass
public class BaseLegalEntity implements Serializable {

    @Id
    @GeneratedValue
    protected UUID uuid;

    /**
     * Наименование юр. лица.
     * Обязательный элемент.
     * Минимум 1 символ
     */
    @Column(name = "full_name", nullable = false)
    protected String fullName;

    @Column(name = "short_name", nullable = false)
    protected String shortName;

    /**
     * Основной государственный регистрационный номер.
     * Обязательный элемент.
     * Формат: ххххххххххххх 13 цифр
     */
    @Column(nullable = false, length = 13)
    protected String ogrn;

    /**
     * Идентификационный номер налогоплательщика.
     * Обязательный элемент.
     * хххххххххх(хх)
     * 10 обязательных и 2 необязательных
     */
    @Column(nullable = false, length = 12)
    protected String inn;

    /**
     * Адрес (Почтовый).
     * Обязательный элемент
     */
    protected String address;

    /**
     * Телефон/Факс.
     * Необязательный элемент.
     * Минимум 1 символ
     */
    protected String phone;

    /**
     * Роли, в качестве которых может выступать юридическое лицо
     */
    @ElementCollection
    @Enumerated(EnumType.STRING)
    protected Set<Role> roleSet = new HashSet<>();

}
