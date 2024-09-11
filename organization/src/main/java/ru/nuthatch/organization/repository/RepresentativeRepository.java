package ru.nuthatch.organization.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.nuthatch.organization.entity.Representative;

import java.util.Collection;
import java.util.UUID;

@Repository
public interface RepresentativeRepository extends CommonRepository<Representative> {

    @Query(value = "SELECT r.uuid, r.last_name, r.first_name, r.middle_name, " +
            "r.administrative_document, r.position, r.nostroy_number, r.organization_uuid " +
            "FROM representative AS r " +
            "RIGHT OUTER JOIN " +
            "(SELECT owos.uuid FROM organization_with_optional_sro AS owos " +
            "WHERE owos.legal_entity_uuid = :uuid OR owos.individual_entrepreneur_uuid = :uuid) AS ou " +
            "ON ou.uuid = r.organization_uuid " +
            "ORDER BY r.last_name, r.first_name, r.middle_name",
            nativeQuery = true)
    Collection<Representative> findAllByLegalEntityOrIndividualEntrepreneurUuid(@Param(value = "uuid") UUID uuid);
}
