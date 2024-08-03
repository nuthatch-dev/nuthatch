package ru.nuthatch.generalworkjournal.service;

import ru.nuthatch.generalworkjournal.common.CommonEntity;
import ru.nuthatch.generalworkjournal.repository.CommonRepository;

import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

public abstract class CommonService<E extends CommonEntity, R extends CommonRepository<E>> {

    protected final R repository;

    protected CommonService(R repository) {
        this.repository = repository;
    }

    // Common CRUD methods

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
        return repository.findById(entity.getUuid()).map(repository::save);
    }

    public void deleteById(UUID uuid) {
        repository.deleteById(uuid);
    }

}
