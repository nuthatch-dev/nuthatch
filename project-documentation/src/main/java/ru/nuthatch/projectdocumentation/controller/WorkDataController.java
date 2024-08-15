package ru.nuthatch.projectdocumentation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.nuthatch.projectdocumentation.entity.WorkData;
import ru.nuthatch.projectdocumentation.entity.WorkVolumeInfo;
import ru.nuthatch.projectdocumentation.service.WorkDataService;

import java.util.Collection;
import java.util.UUID;

@RestController
@RequestMapping(value = "/api/v1/work-data")
public class WorkDataController {

    private final WorkDataService service;

    @Autowired
    public WorkDataController(WorkDataService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<WorkData> save(@RequestBody WorkData entity) {
        return new ResponseEntity<>(service.save(entity), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<WorkData> findById(@RequestParam(name = "id") UUID uuid) {
        return service
                .findById(uuid)
                .map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping(value = "/all")
    public ResponseEntity<Collection<WorkData>> findAll() {
        return new ResponseEntity<>(service.findAll(), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<WorkData> update(@RequestBody WorkData entity) {
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

    @GetMapping(value = "/all-work-volume-info")
    public ResponseEntity<Collection<WorkVolumeInfo>> findAllWorkVolumeInfo(@RequestParam(name = "id") UUID uuid) {
        return new ResponseEntity<>(service.findAllWorkVolumeInfo(uuid), HttpStatus.OK);
    }
}
