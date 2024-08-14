package ru.nuthatch.projectdocumentation.service;

import org.springframework.stereotype.Service;
import ru.nuthatch.projectdocumentation.entity.RevisionInfo;
import ru.nuthatch.projectdocumentation.repository.RevisionInfoRepository;

@Service
public class RevisionInfoService extends CommonService<RevisionInfo, RevisionInfoRepository> {

    protected RevisionInfoService(RevisionInfoRepository repository) {
        super(repository);
    }
}
