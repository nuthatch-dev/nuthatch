package ru.nuthatch.organization.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
import ru.nuthatch.organization.entity.CommonEntity;

import java.util.UUID;

@NoRepositoryBean
public interface CommonRepository <E extends CommonEntity> extends JpaRepository<E, UUID> {
}
