package ru.nuthatch.documentation.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.nuthatch.documentation.entity.Node;

import java.util.Collection;
import java.util.UUID;

@Repository
public interface NodeRepository extends CommonRepository<Node> {

    @Query(value = "SELECT * FROM node AS n WHERE n.parent_node_uuid = :uuid ORDER BY n.name",
            nativeQuery = true)
    Collection<Node> findAllByParentNodeUuid(@Param(value = "uuid") UUID uuid);
}
