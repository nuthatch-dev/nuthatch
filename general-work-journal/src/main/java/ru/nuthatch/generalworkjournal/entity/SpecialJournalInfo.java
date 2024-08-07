package ru.nuthatch.generalworkjournal.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Data
@Embeddable
public class SpecialJournalInfo {

    /**
     * Наименование
     * Обязательный элемент
     * Минимум 1 символ
     */
    @Column(nullable = false)
    protected String name;

    /**
     * Дата документа
     * Обязательный элемент
     * Дата в формате <ГГГГ-ММ-ДД> (год-месяц-день)
     */
    @Column(nullable = false)
    protected Date date;

    /**
     * Список лиц, осуществляющих строительство или лиц,
     * осуществляющих подготовку проектной документации,
     * ведущих журнал, их уполномоченных представителей
     * Обязательный элемент
     * Список
     */
    @ElementCollection
    protected Set<UUID> journalKeepersSet = new HashSet<>();
}
