package ru.nuthatch.documentation.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;

/**
 * Описание комплексного типа: docInfoGroup
 * Группа, содержащая наименование документа и номер(код/шифр) документа
 */
@Data
@Embeddable
public class DocInfoGroup {

    /*
    Наименование документа
    Обязательный элемент
    Минимум 1 символ
     */
    @Column(nullable = false)
    protected String name;

    /*
    Номер/код/шифр документа
    Обязательный элемент
    Минимум 1 символ
     */
    @Column(nullable = false)
    protected String number;

}
