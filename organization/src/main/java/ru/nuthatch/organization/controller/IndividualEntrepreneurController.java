package ru.nuthatch.organization.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.nuthatch.organization.entity.IndividualEntrepreneur;
import ru.nuthatch.organization.service.IndividualEntrepreneurService;

import java.util.Collection;
import java.util.UUID;

@RestController
@RequestMapping(value = "/api/v1/individual-entrepreneur")
public class IndividualEntrepreneurController {

    private final IndividualEntrepreneurService service;

    @Autowired
    public IndividualEntrepreneurController(IndividualEntrepreneurService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<IndividualEntrepreneur> save(@RequestBody IndividualEntrepreneur entity) {
        return new ResponseEntity<>(service.save(entity), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<IndividualEntrepreneur> findById(@RequestParam(name = "id") UUID uuid) {
        return service
                .findById(uuid)
                .map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping(value = "/all-by-role")
    public ResponseEntity<Collection<IndividualEntrepreneur>> findAllByRole(@RequestParam(name = "role") String role) {
        return new ResponseEntity<>(service.findAllByRole(role), HttpStatus.OK);
    }

    @GetMapping(value = "/all")
    public ResponseEntity<Collection<IndividualEntrepreneur>> findAll() {
        return new ResponseEntity<>(service.findAll(), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<IndividualEntrepreneur> update(@RequestBody IndividualEntrepreneur entity) {
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
