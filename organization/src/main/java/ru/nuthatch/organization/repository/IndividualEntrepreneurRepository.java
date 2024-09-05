package ru.nuthatch.organization.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.nuthatch.organization.entity.IndividualEntrepreneur;

import java.util.Collection;

@Repository
public interface IndividualEntrepreneurRepository extends CommonRepository<IndividualEntrepreneur> {

    @Query(value = "SELECT * FROM individual_entrepreneur AS i " +
            "RIGHT OUTER JOIN " +
            "(SELECT irs.individual_entrepreneur_uuid FROM individual_entrepreneur_role_set AS irs " +
            "WHERE irs.role_set = :role) AS s " +
            "ON i.uuid = s.individual_entrepreneur_uuid " +
            "ORDER BY i.last_name",
            nativeQuery = true)
    Collection<IndividualEntrepreneur> findAllByRole(@Param("role") String role);
}
