package ru.nuthatch.organization.service;

import org.springframework.stereotype.Service;
import ru.nuthatch.organization.entity.LegalEntity;
import ru.nuthatch.organization.repository.LegalEntityRepository;

import java.util.Collection;
import java.util.UUID;

@Service
public class LegalEntityService extends CommonService<LegalEntity, LegalEntityRepository> {

    public LegalEntityService(LegalEntityRepository repository) {
        super(repository);
    }

    public Collection<LegalEntity> findAllByRoleUuid(UUID uuid) {
        return repository.findAllByRoleUuid(uuid);
    }
}
