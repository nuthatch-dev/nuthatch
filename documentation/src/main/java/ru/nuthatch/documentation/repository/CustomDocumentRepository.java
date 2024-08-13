package ru.nuthatch.documentation.repository;

import org.springframework.stereotype.Repository;
import ru.nuthatch.documentation.entity.CustomDocument;

@Repository
public interface CustomDocumentRepository extends CommonRepository<CustomDocument> {
}
