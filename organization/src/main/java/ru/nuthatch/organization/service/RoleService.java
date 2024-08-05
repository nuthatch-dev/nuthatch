package ru.nuthatch.organization.service;

import org.springframework.stereotype.Service;
import ru.nuthatch.organization.entity.Role;
import ru.nuthatch.organization.repository.RoleRepository;

@Service
public class RoleService extends CommonService<Role, RoleRepository> {

    public RoleService(RoleRepository repository) {
        super(repository);
    }
}
