package ru.nuthatch.projectdocumentation.service;

import org.springframework.stereotype.Service;
import ru.nuthatch.projectdocumentation.entity.Unit;
import ru.nuthatch.projectdocumentation.repository.UnitRepository;

@Service
public class UnitService extends CommonService<Unit, UnitRepository> {

    protected UnitService(UnitRepository repository) {
        super(repository);
    }
}
