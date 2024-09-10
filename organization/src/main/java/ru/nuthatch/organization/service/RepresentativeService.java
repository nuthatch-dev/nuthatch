package ru.nuthatch.organization.service;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ru.nuthatch.organization.entity.Representative;
import ru.nuthatch.organization.repository.RepresentativeRepository;

import java.util.Collection;
import java.util.UUID;

@Service
public class RepresentativeService extends CommonService<Representative, RepresentativeRepository> {

    public RepresentativeService(RepresentativeRepository repository) {
        super(repository);
    }

    @Override
    public Collection<Representative> findAll() {
        return repository.findAll(Sort.by(Sort.Direction.ASC, "fullNameGroup.lastName"));
    }

    // Выбрать всех представителей по uuid организации
    public Collection<Representative> findAllByLegalEntityOrIndividualEntrepreneurUuid(UUID uuid) {
        return repository.findAllByLegalEntityOrIndividualEntrepreneurUuid(uuid);
    }
}
