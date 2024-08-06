package ru.nuthatch.organization.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.nuthatch.organization.entity.IndividualEntrepreneur;

import java.util.Collection;
import java.util.UUID;

@Repository
public interface IndividualEntrepreneurRepository extends CommonRepository<IndividualEntrepreneur> {

    @Query(value = "SELECT * FROM individual_entrepreneur AS i " +
            "LEFT OUTER JOIN " +
            "(SELECT irs.individual_entrepreneur_uuid FROM individual_entrepreneur_role_set AS irs " +
            "WHERE irs.role_set_uuid = :uuid) AS is " +
            "ON i.uuid = is.individual_entrepreneur_uuid " +
            "ORDER BY i.last_name",
            nativeQuery = true)
    Collection<IndividualEntrepreneur> findAllByRoleUuid(@Param("uuid") UUID uuid);
}
