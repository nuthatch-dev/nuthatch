package ru.nuthatch.projectdocumentation.service;

import org.springframework.stereotype.Service;
import ru.nuthatch.projectdocumentation.entity.GraphicPart;
import ru.nuthatch.projectdocumentation.entity.ProjectInfo;
import ru.nuthatch.projectdocumentation.entity.Specification;
import ru.nuthatch.projectdocumentation.entity.VolumeSheet;
import ru.nuthatch.projectdocumentation.repository.ProjectInfoRepository;

import java.util.Collection;
import java.util.UUID;

@Service
public class ProjectInfoService extends CommonService<ProjectInfo, ProjectInfoRepository> {

    protected ProjectInfoService(ProjectInfoRepository repository) {
        super(repository);
    }

    public Collection<GraphicPart> findAllGraphicPart(UUID uuid) {
        return repository.findAllGraphicPart_Named(uuid);
    }

    public Collection<VolumeSheet> findAllVolumeSheet(UUID uuid) {
        return repository.findAllVolumeSheet_Named(uuid);
    }

    public Collection<Specification> findAllSpecification(UUID uuid) {
        return repository.findAllSpecification_Named(uuid);
    }
}
