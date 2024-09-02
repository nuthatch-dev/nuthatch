package ru.nuthatch.organization.service;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ru.nuthatch.organization.entity.Individual;
import ru.nuthatch.organization.repository.IndividualRepository;

import java.util.Collection;

@Service
public class IndividualService extends CommonService<Individual, IndividualRepository> {

    public IndividualService(IndividualRepository repository) {
        super(repository);
    }

    @Override
    public Collection<Individual> findAll() {
        return repository.findAll(Sort.by(Sort.Direction.ASC, "fullNameGroup.lastName"));
    }

    public Collection<Individual> findAllByRole(String role) {
        return this.repository.findAllByRole(role);
    }
}
