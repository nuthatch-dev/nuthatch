package ru.nuthatch.projectdocumentation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.nuthatch.projectdocumentation.entity.VolumeSheet;
import ru.nuthatch.projectdocumentation.entity.WorkData;
import ru.nuthatch.projectdocumentation.service.VolumeSheetService;

import java.util.Collection;
import java.util.UUID;

@RestController
@RequestMapping(value = "/api/v11/volume-sheet")
public class VolumeSheetController {

    private final VolumeSheetService service;

    @Autowired
    public VolumeSheetController(VolumeSheetService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<VolumeSheet> save(@RequestBody VolumeSheet entity) {
        return new ResponseEntity<>(service.save(entity), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<VolumeSheet> findById(@RequestParam(name = "id") UUID uuid) {
        return service
                .findById(uuid)
                .map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping(value = "/all")
    public ResponseEntity<Collection<VolumeSheet>> findAll() {
        return new ResponseEntity<>(service.findAll(), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<VolumeSheet> update(@RequestBody VolumeSheet entity) {
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

    @GetMapping(value = "/all-work-data")
    public ResponseEntity<Collection<WorkData>> findAllWorkData(@RequestParam(name = "id") UUID uuid) {
        return new ResponseEntity<>(service.findAllWorkData(uuid), HttpStatus.OK);
    }
}
