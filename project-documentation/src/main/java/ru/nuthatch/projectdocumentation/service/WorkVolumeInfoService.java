package ru.nuthatch.projectdocumentation.service;

import org.springframework.stereotype.Service;
import ru.nuthatch.projectdocumentation.entity.WorkVolumeInfo;
import ru.nuthatch.projectdocumentation.repository.WorkVolumeInfoRepository;

@Service
public class WorkVolumeInfoService extends CommonService<WorkVolumeInfo, WorkVolumeInfoRepository> {

    protected WorkVolumeInfoService(WorkVolumeInfoRepository repository) {
        super(repository);
    }
}
