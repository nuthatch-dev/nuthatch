package ru.nuthatch.generalworkjournal.common;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.AttributeOverrides;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;

@Data
@Embeddable
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
