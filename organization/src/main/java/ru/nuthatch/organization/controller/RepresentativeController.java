package ru.nuthatch.organization.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.nuthatch.organization.entity.Representative;
import ru.nuthatch.organization.service.RepresentativeService;

import java.util.Collection;
import java.util.UUID;

@RestController
@RequestMapping(value = "/api/v1/representative")
public class RepresentativeController {
    
    private final RepresentativeService service;

    @Autowired
    public RepresentativeController(RepresentativeService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Representative> save(@RequestBody Representative entity) {
        return new ResponseEntity<>(service.save(entity), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<Representative> findById(@RequestParam(name = "id") UUID uuid) {
        return service
                .findById(uuid)
                .map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping(value = "/all")
    public ResponseEntity<Collection<Representative>> findAll() {
        return new ResponseEntity<>(service.findAll(), HttpStatus.OK);
    }

    @GetMapping(value = "/all-by-counterparty")
    public ResponseEntity<Collection<Representative>> findAllByLegalEntityOrIndividualEntrepreneurUuid(
            @RequestParam(name = "id") UUID uuid) {
        return new ResponseEntity<>(service.findAllByLegalEntityOrIndividualEntrepreneurUuid(uuid), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<Representative> update(@RequestBody Representative entity) {
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
