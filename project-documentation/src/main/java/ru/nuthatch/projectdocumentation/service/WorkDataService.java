package ru.nuthatch.projectdocumentation.service;

import org.springframework.stereotype.Service;
import ru.nuthatch.projectdocumentation.entity.WorkData;
import ru.nuthatch.projectdocumentation.entity.WorkVolumeInfo;
import ru.nuthatch.projectdocumentation.repository.WorkDataRepository;

import java.util.Collection;
import java.util.UUID;

@Service
public class WorkDataService extends CommonService<WorkData, WorkDataRepository> {

    protected WorkDataService(WorkDataRepository repository) {
        super(repository);
    }

    public Collection<WorkVolumeInfo> findAllWorkVolumeInfo(UUID uuid) {
        return repository.findAllWorkVolumeInfo_Named(uuid);
    }
}
