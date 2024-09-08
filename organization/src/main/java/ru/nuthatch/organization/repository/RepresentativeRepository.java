package ru.nuthatch.organization.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.nuthatch.organization.entity.Representative;

import java.util.Collection;
import java.util.UUID;

@Repository
public interface RepresentativeRepository extends CommonRepository<Representative> {

    @Query(value = "SELECT * FROM representative AS r " +
            "WHERE r.legal_entity_uuid = :uuid OR r.individual_entrepreneur_uuid = :uuid " +
            "ORDER BY r.last_name, r.first_name, r.middle_name",
            nativeQuery = true)
    Collection<Representative> findAllByLegalEntityOrIndividualEntrepreneurUuid(@Param(value = "uuid") UUID uuid);
}
