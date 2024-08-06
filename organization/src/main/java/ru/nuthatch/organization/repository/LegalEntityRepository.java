package ru.nuthatch.organization.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.nuthatch.organization.entity.LegalEntity;

import java.util.Collection;
import java.util.UUID;

@Repository
public interface LegalEntityRepository extends CommonRepository<LegalEntity> {

    @Query(value = "SELECT * FROM legal_entity AS l " +
            "LEFT OUTER JOIN " +
            "(SELECT lrs.legal_entity_uuid FROM legal_entity_role_set AS lrs WHERE lrs.role_set_uuid = :uuid) AS ls " +
            "ON l.uuid = ls.legal_entity_uuid " +
            "ORDER BY l.short_name",
            nativeQuery = true)
    Collection<LegalEntity> findAllByRoleUuid(@Param("uuid") UUID uuid);
}
