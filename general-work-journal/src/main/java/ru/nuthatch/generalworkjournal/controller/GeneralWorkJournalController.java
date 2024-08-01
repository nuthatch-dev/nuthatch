package ru.nuthatch.generalworkjournal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.nuthatch.generalworkjournal.common.BaseDocument;
import ru.nuthatch.generalworkjournal.dto.TitleChangeDto;
import ru.nuthatch.generalworkjournal.entity.GeneralWorkJournal;
import ru.nuthatch.generalworkjournal.service.GeneralWorkJournalService;

import java.util.Collection;

@RestController
@RequestMapping(value = "/api/v1/general-work-journal")
public class GeneralWorkJournalController {

    private final GeneralWorkJournalService service;

    @Autowired
    public GeneralWorkJournalController(GeneralWorkJournalService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<GeneralWorkJournal> save(@RequestBody GeneralWorkJournal journal) {
        return new ResponseEntity<>(service.save(journal), HttpStatus.CREATED);
    }

    @GetMapping(value = "/get-by-pk")
    public ResponseEntity<GeneralWorkJournal> findByPK(@RequestBody BaseDocument document) {
        return service
                .findByPK(document)
                .map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping(value = "/all")
    public ResponseEntity<Collection<GeneralWorkJournal>> findAllNotArchived() {
        return new ResponseEntity<>(service.findAllNotArchived(), HttpStatus.OK);
    }

    @GetMapping(value = "/archive")
    public ResponseEntity<Collection<GeneralWorkJournal>> findAllArchived() {
        return new ResponseEntity<>(service.findAllArchived(), HttpStatus.OK);
    }

    // Получить все изменения титульного листа ОЖР
    @GetMapping(value = "/title-changes")
    public ResponseEntity<Collection<TitleChangeDto>> findTitleChanges(@RequestBody BaseDocument document) {
        Collection<TitleChangeDto> result = service.findTitleChanges(document);
        if (result.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    /*
    Внесение изменений в титульный лист журнала работ выполняется только внесением специальной записи
    об изменениях (GeneralWorkJournalTitleChange).
    Удаление Общего Журнала Работ не предусматривается.
    В качестве update/delete операции предусматривается установка аттрибута "архивный" для ОЖР
     */
    @PutMapping(value = "/set-archived")
    public ResponseEntity<Boolean> ChangeArchivedAttribute(@RequestBody BaseDocument document) {
        Boolean result = service.ChangeArchivedAttribute(document);
        if (result != null) {
            return new ResponseEntity<>(result, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
