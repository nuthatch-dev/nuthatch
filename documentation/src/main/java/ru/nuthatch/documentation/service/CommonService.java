package ru.nuthatch.documentation.service;

import ru.nuthatch.documentation.entity.CommonEntity;
import ru.nuthatch.documentation.repository.CommonRepository;

import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

public abstract class CommonService <E extends CommonEntity, R extends CommonRepository<E>> {

    protected final CommonRepository<E> repository;


    protected CommonService(CommonRepository<E> repository) {
        this.repository = repository;
    }

    // Base CRUD methods

    public E save(E entity) {
        return repository.save(entity);
    }

    public Optional<E> findById(UUID uuid) {
        return repository.findById(uuid);
    }

    public Collection<E> findAll() {
        return repository.findAll();
    }

    public Optional<E> update(E entity) {
        return repository.existsById(entity.getUuid())?
                Optional.of(repository.save(entity)) :
                Optional.empty();
    }

    public void deleteById(UUID uuid) {
        repository.deleteById(uuid);
    }
}
