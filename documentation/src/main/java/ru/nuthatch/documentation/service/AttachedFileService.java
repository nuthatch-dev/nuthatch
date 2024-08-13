package ru.nuthatch.documentation.service;

import org.springframework.stereotype.Service;
import ru.nuthatch.documentation.entity.AttachedFile;
import ru.nuthatch.documentation.repository.AttachedFileRepository;
import ru.nuthatch.documentation.repository.CommonRepository;

@Service
public class AttachedFileService extends CommonService<AttachedFile, AttachedFileRepository> {

    public AttachedFileService(CommonRepository<AttachedFile> repository) {
        super(repository);
    }
}
