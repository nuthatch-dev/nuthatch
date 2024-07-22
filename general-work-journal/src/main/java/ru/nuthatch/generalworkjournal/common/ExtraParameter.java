package ru.nuthatch.generalworkjournal.common;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * Дополнительный параметр
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "extra_parameter")
public class ExtraParameter extends CommonEntity implements Serializable {

    @Column(name = "parameter_name",
            nullable = false)
    protected String parameterName;

    @Column(name = "parameter_value",
            nullable = false)
    protected String parameterValue;

}
