package ru.nuthatch.projectdocumentation.entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;

import java.io.Serializable;
import java.util.UUID;

@Data
@MappedSuperclass
public abstract class CommonEntity implements Serializable {

    @Id
    @GeneratedValue
    protected UUID uuid;
}
