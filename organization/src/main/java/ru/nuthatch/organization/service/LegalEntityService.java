package ru.nuthatch.organization.service;

import org.springframework.stereotype.Service;
import ru.nuthatch.organization.entity.LegalEntity;
import ru.nuthatch.organization.repository.LegalEntityRepository;

@Service
public class LegalEntityService extends CommonService<LegalEntity, LegalEntityRepository> {

    public LegalEntityService(LegalEntityRepository repository) {
        super(repository);
    }
}
