package ru.nuthatch.organization.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.nuthatch.organization.entity.Individual;
import ru.nuthatch.organization.service.IndividualService;

import java.util.Collection;
import java.util.UUID;

@RestController
@RequestMapping(value = "/api/v1/individual")
public class IndividualController {

    private final IndividualService service;

    @Autowired
    public IndividualController(IndividualService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Individual> save(@RequestBody Individual entity) {
        return new ResponseEntity<>(service.save(entity), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<Individual> findById(@RequestParam(name = "id") UUID uuid) {
        return service
                .findById(uuid)
                .map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping(value = "/all-by-role")
    public ResponseEntity<Collection<Individual>> findAllByRole(@RequestParam(name = "role") String role) {
        return new ResponseEntity<>(service.findAllByRole(role), HttpStatus.OK);
    }

    @GetMapping(value = "/all")
    public ResponseEntity<Collection<Individual>> findAll() {
        return new ResponseEntity<>(service.findAll(), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<Individual> update(@RequestBody Individual entity) {
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
