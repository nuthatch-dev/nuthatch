package ru.nuthatch.projectdocumentation.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.nuthatch.projectdocumentation.entity.WorkData;
import ru.nuthatch.projectdocumentation.entity.WorkVolumeInfo;

import java.util.Collection;
import java.util.UUID;

@Repository
public interface WorkDataRepository extends CommonRepository<WorkData> {

    @Query(nativeQuery = true)
    Collection<WorkVolumeInfo> findAllWorkVolumeInfo_Named(@Param(value = "uuid") UUID uuid);
}
