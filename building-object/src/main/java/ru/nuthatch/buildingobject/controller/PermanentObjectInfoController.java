package ru.nuthatch.buildingobject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.nuthatch.buildingobject.entity.PermanentObjectInfo;
import ru.nuthatch.buildingobject.service.PermanentObjectInfoService;

@RestController
@RequestMapping(value = "/api/v1/object-info")
public class PermanentObjectInfoController {

    private final PermanentObjectInfoService service;

    @Autowired
    public PermanentObjectInfoController(PermanentObjectInfoService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<PermanentObjectInfo> save(@RequestBody PermanentObjectInfo entity) {
        return new ResponseEntity<>(service.save(entity), HttpStatus.CREATED);
    }
}
