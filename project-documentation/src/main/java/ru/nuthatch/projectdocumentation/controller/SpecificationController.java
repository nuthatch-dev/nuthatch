package ru.nuthatch.projectdocumentation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.nuthatch.projectdocumentation.entity.MaterialData;
import ru.nuthatch.projectdocumentation.entity.Specification;
import ru.nuthatch.projectdocumentation.service.SpecificationService;

import java.util.Collection;
import java.util.UUID;

@RestController
@RequestMapping(value = "/api/v1/specification")
public class SpecificationController {

    private final SpecificationService service;

    @Autowired
    public SpecificationController(SpecificationService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Specification> save(@RequestBody Specification entity) {
        return new ResponseEntity<>(service.save(entity), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<Specification> findById(@RequestParam(name = "id") UUID uuid) {
        return service
                .findById(uuid)
                .map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping(value = "/all")
    public ResponseEntity<Collection<Specification>> findAll() {
        return new ResponseEntity<>(service.findAll(), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<Specification> update(@RequestBody Specification entity) {
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

    @GetMapping(value = "/all-material-data")
    public ResponseEntity<Collection<MaterialData>> findAllMaterialData(@RequestParam(name = "id") UUID uuid) {
        return new ResponseEntity<>(service.findAllMaterialData(uuid), HttpStatus.OK);
    }
}
