package ru.nuthatch.documentation.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Описание комплексного типа: CustomDocument
 * Распорядительный документ
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "custom_document")
public class CustomDocument extends CommonEntity {
    /*
    Группа, содержащая наименование документа и номер(код/шифр) документа
    Обязательный элемент
     */
    @Embedded
    protected DocInfoGroup docInfoGroup;

    /*
    Дата выдачи
    Обязательный элемент
    Дата в формате <ГГГГ-ММ-ДД> (год-месяц-день)
     */
    protected Date date;

    /*
    Дата начала действия
    (заполняется, если дата начала действия отличается от даты формирования документа)
    Необязательный элемент
    Дата в формате <ГГГГ-ММ-ДД> (год-месяц-день)
     */
    protected Date beginningDate;

    /*
    Срок действия
    Необязательный элемент
    Дата в формате <ГГГГ-ММ-ДД> (год-месяц-день)
     */
    protected Date expirationDate;

    /*
    Информация о документе, загруженном в хранилище
     */
    @ManyToOne
    protected AttachedFile attachedFile;

    /*
    Document tags
     */
    @ElementCollection
    protected Set<String> tagSet = new HashSet<>();
}
