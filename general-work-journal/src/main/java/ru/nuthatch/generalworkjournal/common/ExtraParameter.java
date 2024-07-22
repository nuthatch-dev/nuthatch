package ru.nuthatch.generalworkjournal.common;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.util.UUID;

/**
 * Дополнительный параметр
 */
@Data
@Entity
@Table(name = "extra_parameter")
public class ExtraParameter implements Serializable {

    @Id
    @GeneratedValue
    protected UUID uuid;

    @Column(name = "parameter_name",
            nullable = false)
    protected String parameterName;

    @Column(name = "parameter_value",
            nullable = false)
    protected String parameterValue;

}
