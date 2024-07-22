package ru.nuthatch.generalworkjournal.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import ru.nuthatch.generalworkjournal.common.CommonEntity;
import ru.nuthatch.generalworkjournal.common.DocInfo;
import ru.nuthatch.generalworkjournal.common.Representative;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Описание комплексного типа: AsBuiltDocumentation
 * Элемент исполнительной документации при строительстве, реконструкции,
 * капитальном ремонте объекта капитального строительства
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "as_built_documentation")
public class AsBuiltDocumentation extends CommonEntity implements Serializable {

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
    @JoinTable(name = "as_built_doc_representative")
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
