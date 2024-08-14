package ru.nuthatch.projectdocumentation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.nuthatch.projectdocumentation.entity.GraphicPart;
import ru.nuthatch.projectdocumentation.entity.ProjectInfo;
import ru.nuthatch.projectdocumentation.entity.Specification;
import ru.nuthatch.projectdocumentation.entity.VolumeSheet;
import ru.nuthatch.projectdocumentation.service.ProjectInfoService;

import java.util.Collection;
import java.util.UUID;

@RestController
@RequestMapping(value = "/api/v1/project-info")
public class ProjectInfoController {
    
    private final ProjectInfoService service;

    @Autowired
    public ProjectInfoController(ProjectInfoService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<ProjectInfo> save(@RequestBody ProjectInfo entity) {
        return new ResponseEntity<>(service.save(entity), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<ProjectInfo> findById(@RequestParam(name = "id") UUID uuid) {
        return service
                .findById(uuid)
                .map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping(value = "/all")
    public ResponseEntity<Collection<ProjectInfo>> findAll() {
        return new ResponseEntity<>(service.findAll(), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<ProjectInfo> update(@RequestBody ProjectInfo entity) {
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

    @GetMapping(value = "/all-graphic-part")
    public ResponseEntity<Collection<GraphicPart>> findAllGraphicPart(@RequestParam(name = "id") UUID uuid) {
        return new ResponseEntity<>(service.findAllGraphicPart(uuid), HttpStatus.OK);
    }

    @GetMapping(value = "/all-volume-sheet")
    public ResponseEntity<Collection<VolumeSheet>> findAllVolumeSheet(@RequestParam(name = "id") UUID uuid) {
        return new ResponseEntity<>(service.findAllVolumeSheet(uuid), HttpStatus.OK);
    }

    @GetMapping(value = "/all-specification")
    public ResponseEntity<Collection<Specification>> findAllSpecification(@RequestParam(name = "id") UUID uuid) {
        return new ResponseEntity<>(service.findAllSpecification(uuid), HttpStatus.OK);
    }
}
