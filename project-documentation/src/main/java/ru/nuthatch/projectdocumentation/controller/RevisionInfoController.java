package ru.nuthatch.projectdocumentation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.nuthatch.projectdocumentation.entity.RevisionInfo;
import ru.nuthatch.projectdocumentation.service.RevisionInfoService;

import java.util.Collection;
import java.util.UUID;

@RestController
@RequestMapping(value = "/api/v1/revision-info")
public class RevisionInfoController {

    private final RevisionInfoService service;

    @Autowired
    public RevisionInfoController(RevisionInfoService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<RevisionInfo> save(@RequestBody RevisionInfo entity) {
        return new ResponseEntity<>(service.save(entity), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<RevisionInfo> findById(@RequestParam(name = "id") UUID uuid) {
        return service
                .findById(uuid)
                .map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping(value = "/all")
    public ResponseEntity<Collection<RevisionInfo>> findAll() {
        return new ResponseEntity<>(service.findAll(), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<RevisionInfo> update(@RequestBody RevisionInfo entity) {
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
