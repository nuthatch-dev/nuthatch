package ru.nuthatch.organization.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.nuthatch.organization.entity.Individual;

import java.util.Collection;
import java.util.UUID;

@Repository
public interface IndividualRepository extends CommonRepository<Individual> {

    @Query(value = "SELECT * FROM individual AS i " +
            "LEFT OUTER JOIN " +
            "(SELECT irs.individual_uuid FROM individual_role_set AS irs WHERE irs.role_set_uuid = :uuid) AS is " +
            "ON i.uuid = is.individual_uuid " +
            "ORDER BY i.last_name",
            nativeQuery = true)
    Collection<Individual> findAllByRoleUuid(@Param("uuid") UUID uuid);
}
