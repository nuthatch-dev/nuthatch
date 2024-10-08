package ru.nuthatch.documentation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.nuthatch.documentation.entity.AttachedFile;
import ru.nuthatch.documentation.service.AttachedFileService;
import software.amazon.awssdk.awscore.exception.AwsServiceException;

import java.io.IOException;
import java.util.Collection;
import java.util.UUID;

@RestController
@RequestMapping(value = "/api/v1/attached-file")
public class AttachedFileController {
    
    private final AttachedFileService service;

    @Autowired
    public AttachedFileController(AttachedFileService service) {
        this.service = service;
    }

    @PostMapping(value = "/upload")
    public ResponseEntity<AttachedFile> upload(@RequestParam(name = "file") MultipartFile file) {
        try {
            AttachedFile attachedFile = service.upload(file);
            return new ResponseEntity<>(attachedFile, HttpStatus.OK);
        } catch (IOException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (AwsServiceException e) {
            return new ResponseEntity<>(HttpStatus.SERVICE_UNAVAILABLE);
        }
    }

    @PostMapping
    public ResponseEntity<AttachedFile> save(@RequestBody AttachedFile entity) {
        return new ResponseEntity<>(service.save(entity), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<AttachedFile> findById(@RequestParam(name = "id") UUID uuid) {
        return service
                .findById(uuid)
                .map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping(value = "/all")
    public ResponseEntity<Collection<AttachedFile>> findAll() {
        return new ResponseEntity<>(service.findAll(), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<AttachedFile> update(@RequestBody AttachedFile entity) {
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
