package ru.nuthatch.buildingobject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.nuthatch.buildingobject.entity.PermanentObjectInfo;
import ru.nuthatch.buildingobject.repository.PermanentObjectInfoRepository;

import java.util.Optional;
import java.util.UUID;

@Service
public class PermanentObjectInfoService {

    private final PermanentObjectInfoRepository repository;

    @Autowired
    public PermanentObjectInfoService(PermanentObjectInfoRepository repository) {
        this.repository = repository;
    }

    // CRUD methods

    public PermanentObjectInfo save(PermanentObjectInfo entity) {
        return repository.save(entity);
    }

    public Optional<PermanentObjectInfo> findById(UUID uuid) {
        return repository.findById(uuid);
    }

    public Iterable<PermanentObjectInfo> findAll() {
        return repository.findAll();
    }

    public Optional<PermanentObjectInfo> update(PermanentObjectInfo entity) {
        return repository
                .findById(entity.getUuid())
                .map(value -> repository.save(entity));
    }

    public void deleteById(UUID uuid) {
        repository.deleteById(uuid);
    }
}
