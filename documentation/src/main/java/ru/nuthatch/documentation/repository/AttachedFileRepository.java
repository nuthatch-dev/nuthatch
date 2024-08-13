package ru.nuthatch.documentation.repository;

import org.springframework.stereotype.Repository;
import ru.nuthatch.documentation.entity.AttachedFile;

@Repository
public interface AttachedFileRepository extends CommonRepository<AttachedFile> {
}
