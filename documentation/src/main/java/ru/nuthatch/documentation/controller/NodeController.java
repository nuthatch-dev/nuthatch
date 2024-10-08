package ru.nuthatch.documentation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.nuthatch.documentation.entity.Node;
import ru.nuthatch.documentation.service.NodeService;

import java.util.Collection;
import java.util.UUID;

@RestController
@RequestMapping(value = "/api/v1/node")
public class NodeController {

    private final NodeService service;

    @Autowired
    public NodeController(NodeService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Node> save(@RequestBody Node entity) {
        return new ResponseEntity<>(service.save(entity), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<Node> findById(@RequestParam(name = "id") UUID uuid) {
        return service
                .findById(uuid)
                .map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping(value = "/all-by-parent")
    public ResponseEntity<Collection<Node>> findAllByParentNodeUuid(@RequestParam(name = "id") UUID uuid) {
        return new ResponseEntity<>(service.findAllByParentNodeUuid(uuid), HttpStatus.OK);
    }

    @GetMapping(value = "/all")
    public ResponseEntity<Collection<Node>> findAll() {
        return new ResponseEntity<>(service.findAll(), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<Node> update(@RequestBody Node entity) {
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
