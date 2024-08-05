package ru.nuthatch.organization.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "role")
public class Role extends CommonEntity {

    @Column(name = "role_name",
            nullable = false)
    protected String roleName;

}
