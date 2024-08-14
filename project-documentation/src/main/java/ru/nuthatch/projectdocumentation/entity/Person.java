package ru.nuthatch.projectdocumentation.entity;

import jakarta.persistence.Embeddable;
import lombok.Data;

@Data
@Embeddable
public class Person {

    protected String firstName;
    protected String lastName;
    protected String patronymic;
    protected String phone;

}
