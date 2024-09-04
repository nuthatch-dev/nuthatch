package ru.nuthatch.organization.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.nuthatch.organization.entity.LegalEntity;
import ru.nuthatch.organization.service.LegalEntityService;

import java.util.Collection;
import java.util.UUID;

@RestController
@RequestMapping(value = "/api/v1/legal-entity")
public class LegalEntityController {

    private final LegalEntityService service;

    @Autowired
    public LegalEntityController(LegalEntityService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<LegalEntity> save(@RequestBody LegalEntity entity) {
        return new ResponseEntity<>(service.save(entity), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<LegalEntity> findById(@RequestParam(name = "id") UUID uuid) {
        return service
                .findById(uuid)
                .map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping(value = "/all-by-role")
    public ResponseEntity<Collection<LegalEntity>> findAllByRole(@RequestParam(name = "role") String role) {
        return new ResponseEntity<>(service.findAllByRole(role), HttpStatus.OK);
    }

    @GetMapping(value = "/all")
    public ResponseEntity<Collection<LegalEntity>> findAll() {
        return new ResponseEntity<>(service.findAll(), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<LegalEntity> update(@RequestBody LegalEntity entity) {
        return service
                .update(entity)
                .map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping
    public ResponseEntity<HttpStatus> deleteById(@RequestParam(name = "id") UUID uuid) {
        service.deleteById(uuid);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
