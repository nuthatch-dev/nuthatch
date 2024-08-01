package ru.nuthatch.generalworkjournal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
import ru.nuthatch.generalworkjournal.common.CommonEntity;

import java.util.UUID;

@NoRepositoryBean
public interface CommonRepository <E extends CommonEntity> extends JpaRepository<E, UUID> {
}
