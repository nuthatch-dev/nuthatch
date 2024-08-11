package ru.nuthatch.organization.service;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ru.nuthatch.organization.entity.Individual;
import ru.nuthatch.organization.entity.Role;
import ru.nuthatch.organization.repository.RoleRepository;

import java.util.Collection;

@Service
public class RoleService extends CommonService<Role, RoleRepository> {

    public RoleService(RoleRepository repository) {
        super(repository);
    }

    @Override
    public Collection<Role> findAll() {
        return repository.findAll(Sort.by(Sort.Direction.ASC, "roleName"));
    }
}
