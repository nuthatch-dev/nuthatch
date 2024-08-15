package ru.nuthatch.projectdocumentation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.nuthatch.projectdocumentation.entity.MaterialData;
import ru.nuthatch.projectdocumentation.entity.MaterialVolumeInfo;
import ru.nuthatch.projectdocumentation.service.MaterialDataService;

import java.util.Collection;
import java.util.UUID;

@RestController
@RequestMapping(value = "/api/v1/material-data")
public class MaterialDataController {

    private final MaterialDataService service;

    @Autowired
    public MaterialDataController(MaterialDataService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<MaterialData> save(@RequestBody MaterialData entity) {
        return new ResponseEntity<>(service.save(entity), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<MaterialData> findById(@RequestParam(name = "id") UUID uuid) {
        return service
                .findById(uuid)
                .map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping(value = "/all")
    public ResponseEntity<Collection<MaterialData>> findAll() {
        return new ResponseEntity<>(service.findAll(), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<MaterialData> update(@RequestBody MaterialData entity) {
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

    @GetMapping(value = "/all-material-volume-info")
    public ResponseEntity<Collection<MaterialVolumeInfo>> findAllMaterialVolumeInfo(
            @RequestParam(name = "id") UUID uuid) {
        return new ResponseEntity<>(service.findAllMaterialVolumeInfo(uuid), HttpStatus.OK);
    }
}
