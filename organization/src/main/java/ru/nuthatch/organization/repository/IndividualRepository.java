package ru.nuthatch.organization.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.nuthatch.organization.entity.Individual;

import java.util.Collection;

@Repository
public interface IndividualRepository extends CommonRepository<Individual> {

    @Query(value = "SELECT * FROM individual AS i " +
            "RIGHT OUTER JOIN " +
            "(SELECT irs.individual_uuid FROM individual_role_set AS irs WHERE irs.role_set = :role) AS s " +
            "ON i.uuid = s.individual_uuid " +
            "ORDER BY i.last_name",
            nativeQuery = true)
    Collection<Individual> findAllByRole(@Param("role") String role);
}
