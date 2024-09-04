package ru.nuthatch.baseentity.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;

/**
 * Описание комплексного типа: FullNameGroup.
 * Группа, содержащая Фамилию, Имя, Отчество
 */
@Data
@Embeddable
public class FullNameGroup {

    /**
     * Фамилия. Обязательный элемент
     */
    @Column(name = "last_name", nullable = false)
    protected String lastName;

    /**
     * Имя. Обязательный элемент
     */
    @Column(name = "first_name", nullable = false)
    protected String firstName;

    /**
     * Отчество. Необязательный элемент
     */
    @Column(name = "middle_name")
    protected String middleName;
}
