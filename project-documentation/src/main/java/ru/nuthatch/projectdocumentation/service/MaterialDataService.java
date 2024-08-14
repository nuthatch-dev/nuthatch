package ru.nuthatch.projectdocumentation.service;

import org.springframework.stereotype.Service;
import ru.nuthatch.projectdocumentation.entity.MaterialData;
import ru.nuthatch.projectdocumentation.entity.MaterialVolumeInfo;
import ru.nuthatch.projectdocumentation.repository.MaterialDataRepository;

import java.util.Collection;
import java.util.UUID;

@Service
public class MaterialDataService extends CommonService<MaterialData, MaterialDataRepository> {

    protected MaterialDataService(MaterialDataRepository repository) {
        super(repository);
    }

    public Collection<MaterialVolumeInfo> findAllMaterialVolumeInfo(UUID uuid) {
        return repository.findAllMaterialVolumeInfo_Named(uuid);
    }
}
