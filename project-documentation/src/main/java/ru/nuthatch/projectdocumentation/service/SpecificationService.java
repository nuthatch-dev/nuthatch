package ru.nuthatch.projectdocumentation.service;

import org.springframework.stereotype.Service;
import ru.nuthatch.projectdocumentation.entity.MaterialData;
import ru.nuthatch.projectdocumentation.entity.Specification;
import ru.nuthatch.projectdocumentation.repository.SpecificationRepository;

import java.util.Collection;
import java.util.UUID;

@Service
public class SpecificationService extends CommonService<Specification, SpecificationRepository> {

    protected SpecificationService(SpecificationRepository repository) {
        super(repository);
    }

    public Collection<MaterialData> findAllMaterialData(UUID uuid) {
        return repository.findAllMaterialData_Named(uuid);
    }
}
