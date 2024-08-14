package ru.nuthatch.projectdocumentation.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.nuthatch.projectdocumentation.entity.MaterialData;
import ru.nuthatch.projectdocumentation.entity.Specification;

import java.util.Collection;
import java.util.UUID;

@Repository
public interface SpecificationRepository extends CommonRepository<Specification> {

    @Query(nativeQuery = true)
    Collection<MaterialData> findAllMaterialData_Named(@Param(value = "uuid") UUID uuid);
}
