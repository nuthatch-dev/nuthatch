package ru.nuthatch.organization.service;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ru.nuthatch.organization.entity.Individual;
import ru.nuthatch.organization.entity.Representative;
import ru.nuthatch.organization.repository.RepresentativeRepository;

import java.util.Collection;

@Service
public class RepresentativeService extends CommonService<Representative, RepresentativeRepository> {

    public RepresentativeService(RepresentativeRepository repository) {
        super(repository);
    }

    @Override
    public Collection<Representative> findAll() {
        return repository.findAll(Sort.by(Sort.Direction.ASC, "fullNameGroup.lastName"));
    }
}
