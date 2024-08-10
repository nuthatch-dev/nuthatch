package ru.nuthatch.organization.service;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ru.nuthatch.organization.entity.IndividualEntrepreneur;
import ru.nuthatch.organization.entity.Sro;
import ru.nuthatch.organization.repository.SroRepository;

import java.util.Collection;

@Service
public class SroService extends CommonService<Sro, SroRepository> {

    public SroService(SroRepository repository) {
        super(repository);
    }

    @Override
    public Collection<Sro> findAll() {
        return repository.findAll(Sort.by(Sort.Direction.ASC, "name"));
    }
}
