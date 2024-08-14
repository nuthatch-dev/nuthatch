package ru.nuthatch.projectdocumentation.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
import ru.nuthatch.projectdocumentation.entity.CommonEntity;

import java.util.UUID;

@NoRepositoryBean
public interface CommonRepository <E extends CommonEntity> extends JpaRepository<E, UUID> {
}
