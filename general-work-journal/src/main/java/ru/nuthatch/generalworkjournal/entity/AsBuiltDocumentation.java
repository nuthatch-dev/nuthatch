package ru.nuthatch.generalworkjournal.entity;

import jakarta.persistence.*;
import lombok.Data;
import ru.nuthatch.generalworkjournal.common.DocInfo;
import ru.nuthatch.generalworkjournal.common.Representative;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

/**
 * Описание комплексного типа: AsBuiltDocumentation
 * Элемент исполнительной документации при строительстве, реконструкции,
 * капитальном ремонте объекта капитального строительства
 */
@Data
@Entity
@Table(name = "as_built_documentation")
public class AsBuiltDocumentation implements Serializable {

    @Id
    @GeneratedValue
    protected UUID uuid;

    /**
     * Дата документа
     * Необязательный элемент
     * Дата в формате <ГГГГ-ММ-ДД> (год-месяц-день)
     */
    protected Date date;

    /**
     * Идентификатор документа
     */
    @Embedded
    protected DocInfo docInfo;

    /**
     * Список подписантов документа
     * Обязательный элемент
     * Список
     */
    @ManyToMany
    protected Set<Representative> docSignatoriesSet = new HashSet<>();

    /**
     * Предмет документирования (с указанием вида работ, места расположения конструкций,
     * участков инженерно-технических сетей и т.д.)
     * Обязательный элемент
     * Минимум 1 символ
     */
    protected String docSubject;

    /**
     * Отношение к GeneralWorkJournal
     */
    @ManyToOne
    protected GeneralWorkJournal generalWorkJournal;

}
