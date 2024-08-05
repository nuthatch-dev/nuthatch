package ru.nuthatch.organization.service;

import org.springframework.stereotype.Service;
import ru.nuthatch.organization.entity.PassportDetails;
import ru.nuthatch.organization.repository.PassportDetailsRepository;

@Service
public class PassportDetailsService extends CommonService<PassportDetails, PassportDetailsRepository> {

    public PassportDetailsService(PassportDetailsRepository repository) {
        super(repository);
    }
}
