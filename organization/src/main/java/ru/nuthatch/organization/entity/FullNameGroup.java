package ru.nuthatch.organization.entity;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.AttributeOverrides;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;

/**
 * Описание комплексного типа: FullNameGroup.
 * Группа, содержащая Фамилию, Имя, Отчество
 */
@Data
@Embeddable
@AttributeOverrides({
        @AttributeOverride(name = "last_name", column = @Column(name = "full_name_group_last_name")),
        @AttributeOverride(name = "first_name", column = @Column(name = "full_name_group_first_name")),
        @AttributeOverride(name = "middle_name", column = @Column(name = "full_name_group_middle_name"))
})
public class FullNameGroup {

    /**
     * Фамилия. Обязательный элемент
     */
    @Column(nullable = false)
    protected String lastName;

    /**
     * Имя. Обязательный элемент
     */
    @Column(nullable = false)
    protected String firstName;

    /**
     * Отчество. Необязательный элемент
     */
    protected String middleName;
}
