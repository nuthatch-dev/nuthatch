package ru.nuthatch.documentation.repository;

import org.springframework.stereotype.Repository;
import ru.nuthatch.documentation.entity.Node;

@Repository
public interface NodeRepository extends CommonRepository<Node> {
}
