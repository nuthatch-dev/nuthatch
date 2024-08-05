package ru.nuthatch.organization.service;

import org.springframework.stereotype.Service;
import ru.nuthatch.organization.entity.Sro;
import ru.nuthatch.organization.repository.SroRepository;

@Service
public class SroService extends CommonService<Sro, SroRepository> {

    public SroService(SroRepository repository) {
        super(repository);
    }
}
