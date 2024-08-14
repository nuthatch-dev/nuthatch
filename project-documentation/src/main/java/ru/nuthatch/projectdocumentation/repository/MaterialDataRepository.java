package ru.nuthatch.projectdocumentation.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.nuthatch.projectdocumentation.entity.MaterialData;
import ru.nuthatch.projectdocumentation.entity.MaterialVolumeInfo;

import java.util.Collection;
import java.util.UUID;

@Repository
public interface MaterialDataRepository extends CommonRepository<MaterialData> {

    @Query(nativeQuery = true)
    Collection<MaterialVolumeInfo> findAllMaterialVolumeInfo_Named(@Param(value = "uuid") UUID uuid);
}
