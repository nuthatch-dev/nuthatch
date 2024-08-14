package ru.nuthatch.projectdocumentation.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.nuthatch.projectdocumentation.entity.VolumeSheet;
import ru.nuthatch.projectdocumentation.entity.WorkData;

import java.util.Collection;
import java.util.UUID;

@Repository
public interface VolumeSheetRepository extends CommonRepository<VolumeSheet> {

    @Query(nativeQuery = true)
    Collection<WorkData> findAllWorkData_Named(@Param(value = "uuid") UUID uuid);
}
