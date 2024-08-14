package ru.nuthatch.projectdocumentation.service;

import org.springframework.stereotype.Service;
import ru.nuthatch.projectdocumentation.entity.VolumeSheet;
import ru.nuthatch.projectdocumentation.entity.WorkData;
import ru.nuthatch.projectdocumentation.repository.VolumeSheetRepository;

import java.util.Collection;
import java.util.UUID;

@Service
public class VolumeSheetService extends CommonService<VolumeSheet, VolumeSheetRepository> {

    protected VolumeSheetService(VolumeSheetRepository repository) {
        super(repository);
    }

    public Collection<WorkData> findAllWorkData(UUID uuid) {
        return repository.findAllWorkData_Named(uuid);
    }
}
