package ru.nuthatch.organization.service;

import org.springframework.stereotype.Service;
import ru.nuthatch.organization.entity.Representative;
import ru.nuthatch.organization.repository.RepresentativeRepository;

@Service
public class RepresentativeService extends CommonService<Representative, RepresentativeRepository> {

    public RepresentativeService(RepresentativeRepository repository) {
        super(repository);
    }
}
