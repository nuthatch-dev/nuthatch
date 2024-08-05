package ru.nuthatch.organization.service;

import org.springframework.stereotype.Service;
import ru.nuthatch.organization.entity.IndividualEntrepreneur;
import ru.nuthatch.organization.repository.IndividualEntrepreneurRepository;

@Service
public class IndividualEntrepreneurService extends
        CommonService<IndividualEntrepreneur, IndividualEntrepreneurRepository> {

    public IndividualEntrepreneurService(IndividualEntrepreneurRepository repository) {
        super(repository);
    }
}
