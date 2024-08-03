package ru.nuthatch.generalworkjournal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.nuthatch.generalworkjournal.dto.TitleChangeDto;
import ru.nuthatch.generalworkjournal.entity.AsBuiltDocumentation;
import ru.nuthatch.generalworkjournal.entity.GeneralWorkJournal;
import ru.nuthatch.generalworkjournal.entity.SpecialJournal;
import ru.nuthatch.generalworkjournal.entity.WorksPerformingInfo;
import ru.nuthatch.generalworkjournal.entity.controlevent.ControlEventInfo;
import ru.nuthatch.generalworkjournal.service.GeneralWorkJournalService;

import java.util.Collection;
import java.util.UUID;

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

    @GetMapping(value = "/get-by-id")
    public ResponseEntity<GeneralWorkJournal> findByPK(@RequestParam(name = "uuid") UUID uuid) {
        return service
                .findById(uuid)
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

    /*
    Внесение изменений в титульный лист журнала работ выполняется только внесением специальной записи
    об изменениях (GeneralWorkJournalTitleChange).
    Удаление Общего Журнала Работ не предусматривается.
    В качестве update/delete операции предусматривается установка аттрибута "архивный" для ОЖР
     */
    @PutMapping(value = "/set-archived")
    public ResponseEntity<Boolean> ChangeArchivedAttribute(@RequestParam(name = "uuid") UUID uuid) {
        Boolean result = service.ChangeArchivedAttribute(uuid);
        if (result != null) {
            return new ResponseEntity<>(result, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // Получить все изменения титульного листа ОЖР
    @GetMapping(value = "/title-changes")
    public ResponseEntity<Collection<TitleChangeDto>> findTitleChanges(@RequestParam(name = "uuid") UUID uuid) {
        Collection<TitleChangeDto> result = service.findTitleChanges(uuid);
        if (result.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    // Получение списка специальных журналов
    @GetMapping(value = "/special-journals")
    public ResponseEntity<Collection<SpecialJournal>> findSpecialJournals(@RequestParam(name = "uuid") UUID uuid) {
        return new ResponseEntity<>(service.findSpecialJournals(uuid), HttpStatus.OK);
    }

    // Получение списка сведений о выполненных работах
    @GetMapping(value = "/performing-infos")
    public ResponseEntity<Collection<WorksPerformingInfo>> findWorksPerformingInfos(
            @RequestParam(name = "uuid") UUID uuid) {
        return new ResponseEntity<>(service.findWorksPerformingInfos(uuid), HttpStatus.OK);
    }

    // Получение списка сведений о строительном контроле
    @GetMapping(value = "/control-events")
    public ResponseEntity<Collection<ControlEventInfo>> findControlEventInfos(@RequestParam(name = "uuid") UUID uuid) {
        return new ResponseEntity<>(service.findControlEventInfos(uuid), HttpStatus.OK);
    }

    // Получение перечня исполнительной документации
    @GetMapping(value = "/as-built-docs")
    public ResponseEntity<Collection<AsBuiltDocumentation>> findAsBuiltDocumentation(
            @RequestParam(name = "uuid") UUID uuid) {
        return new ResponseEntity<>(service.findAsBuiltDocumentation(uuid), HttpStatus.OK);
    }

}
