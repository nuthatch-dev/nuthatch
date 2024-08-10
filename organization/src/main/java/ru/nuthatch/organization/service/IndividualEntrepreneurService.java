package ru.nuthatch.organization.service;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ru.nuthatch.organization.entity.Individual;
import ru.nuthatch.organization.entity.IndividualEntrepreneur;
import ru.nuthatch.organization.repository.IndividualEntrepreneurRepository;

import java.util.Collection;
import java.util.UUID;

@Service
public class IndividualEntrepreneurService extends
        CommonService<IndividualEntrepreneur, IndividualEntrepreneurRepository> {

    public IndividualEntrepreneurService(IndividualEntrepreneurRepository repository) {
        super(repository);
    }

    @Override
    public Collection<IndividualEntrepreneur> findAll() {
        return repository.findAll(Sort.by(Sort.Direction.ASC, "fullNameGroup.lastName"));
    }

    public Collection<IndividualEntrepreneur> findAllByRoleUuid(UUID uuid) {
        return repository.findAllByRoleUuid(uuid);
    }
}
