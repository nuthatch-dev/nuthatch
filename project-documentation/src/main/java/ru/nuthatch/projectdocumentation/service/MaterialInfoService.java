package ru.nuthatch.projectdocumentation.service;

import org.springframework.stereotype.Service;
import ru.nuthatch.projectdocumentation.entity.MaterialInfo;
import ru.nuthatch.projectdocumentation.repository.MaterialInfoRepository;

@Service
public class MaterialInfoService extends CommonService<MaterialInfo, MaterialInfoRepository> {

    protected MaterialInfoService(MaterialInfoRepository repository) {
        super(repository);
    }
}
