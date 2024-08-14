package ru.nuthatch.projectdocumentation.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.nuthatch.projectdocumentation.entity.GraphicPart;
import ru.nuthatch.projectdocumentation.entity.ProjectInfo;
import ru.nuthatch.projectdocumentation.entity.Specification;
import ru.nuthatch.projectdocumentation.entity.VolumeSheet;

import java.util.Collection;
import java.util.UUID;

@Repository
public interface ProjectInfoRepository extends CommonRepository<ProjectInfo> {

    // Получить графическую часть по проекту
    @Query(nativeQuery = true)
    Collection<GraphicPart> findAllGraphicPart_Named(@Param(value = "uuid") UUID uuid);

    // Получить ведомости объемов работ по проекту
    @Query(nativeQuery = true)
    Collection<VolumeSheet> findAllVolumeSheet_Named(@Param(value = "uuid") UUID uuid);

    // Получить спецификации по проекту
    @Query(nativeQuery = true)
    Collection<Specification> findAllSpecification_Named(@Param(value = "uuid") UUID uuid);

}
