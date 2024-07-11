package ru.nuthatch.generalworkjournal.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/**
 * Описание комплексного типа: worksPerformingInfo
 * Сведения о выполнении работ в процессе строительства, реконструкции,
 * капитального ремонта объекта капитального строительства (id)
 */
@Data
@Entity
@Table(name = "works_performing_info")
public class WorksPerformingInfo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;
}
