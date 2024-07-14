package ru.nuthatch.generalworkjournal.common;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.AttributeOverrides;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;

@Data
@Embeddable
@AttributeOverrides({
        @AttributeOverride(name = "last_name",
                column = @Column(name = "full_name_group_last_name")),
        @AttributeOverride(name = "first_name",
                column = @Column(name = "full_name_group_first_name")),
        @AttributeOverride(name = "middle_name",
                column = @Column(name = "full_name_group_middle_name"))
})
public class FullNameGroup {

    // Фамилия
    @Column(nullable = false)
    protected String lastName;

    // Имя
    @Column(nullable = false)
    protected String firstName;

    // Отчество
    protected String middleName;
}
