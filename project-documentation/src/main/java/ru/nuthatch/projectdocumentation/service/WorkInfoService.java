package ru.nuthatch.projectdocumentation.service;

import org.springframework.stereotype.Service;
import ru.nuthatch.projectdocumentation.entity.WorkInfo;
import ru.nuthatch.projectdocumentation.repository.WorkInfoRepository;

@Service
public class WorkInfoService extends CommonService<WorkInfo, WorkInfoRepository> {

    protected WorkInfoService(WorkInfoRepository repository) {
        super(repository);
    }
}
