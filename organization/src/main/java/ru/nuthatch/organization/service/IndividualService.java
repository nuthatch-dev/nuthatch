package ru.nuthatch.organization.service;

import org.springframework.stereotype.Service;
import ru.nuthatch.organization.entity.Individual;
import ru.nuthatch.organization.repository.IndividualRepository;

@Service
public class IndividualService extends CommonService<Individual, IndividualRepository> {

    public IndividualService(IndividualRepository repository) {
        super(repository);
    }
}
