package ru.nuthatch.generalworkjournal.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.nuthatch.generalworkjournal.common.BaseDocument;
import ru.nuthatch.generalworkjournal.dto.TitleChangeDto;
import ru.nuthatch.generalworkjournal.entity.AsBuiltDocumentation;
import ru.nuthatch.generalworkjournal.entity.GeneralWorkJournal;
import ru.nuthatch.generalworkjournal.entity.SpecialJournal;
import ru.nuthatch.generalworkjournal.entity.WorksPerformingInfo;
import ru.nuthatch.generalworkjournal.entity.controlevent.ControlEventInfo;
import ru.nuthatch.generalworkjournal.repository.GeneralWorkJournalRepository;

import java.util.Collection;
import java.util.Optional;

@Service
public class GeneralWorkJournalService {

    private final GeneralWorkJournalRepository repository;

    @Autowired
    public GeneralWorkJournalService(GeneralWorkJournalRepository repository) {
        this.repository = repository;
    }

    /*
    CRUD methods
     */
    public GeneralWorkJournal save(GeneralWorkJournal journal) {
        return repository.save(journal);
    }

    public Optional<GeneralWorkJournal> findByPK(BaseDocument document) {
        return repository.findById(document);
    }

    public Collection<GeneralWorkJournal> findAllNotArchived() {
        return repository.findAllNotArchived();
    }

    public Collection<GeneralWorkJournal> findAllArchived() {
        return repository.findAllArchived();
    }

    /*
     Изменить признак "в архиве" для ОЖР.
     Триггер, при выполнении аттрибут "archived" сменяется на противоположный
     */
    public Boolean ChangeArchivedAttribute(BaseDocument pk) {
        return repository
                .findById(pk)
                .map(value -> repository.ChangeArchivedAttribute(
                                value.getBaseDocument().getUuid(),
                                !value.isArchived()
                        )
                )
                .orElseGet(() -> null);
    }

    // Получить все изменения титульного листа ОЖР
    public Collection<TitleChangeDto> findTitleChanges(BaseDocument document) {
        return repository.findAllGeneralWorkJournalTitleChanges_Named(
                document.getUuid(),
                document.getEdition(),
                document.getSchemaVersion());
    }

    // Получение списка специальных журналов
    public Collection<SpecialJournal> findSpecialJournals(BaseDocument document) {
        return repository.findAllSpecialJournals_Named(
                document.getUuid(),
                document.getEdition(),
                document.getSchemaVersion());
    }

    // Получение списка сведений о выполненных работах
    public Collection<WorksPerformingInfo> findWorksPerformingInfos(BaseDocument document) {
        return repository.findAllWorksPerformingInfos_Named(
                document.getUuid(),
                document.getEdition(),
                document.getSchemaVersion());
    }

    // Получение списка сведений о строительном контроле
    public Collection<ControlEventInfo> findControlEventInfos(BaseDocument document) {
        return repository.findAllControlEventInfos_Named(
                document.getUuid(),
                document.getEdition(),
                document.getSchemaVersion());
    }

    // Получение перечня исполнительной документации
    public Collection<AsBuiltDocumentation> findAsBuiltDocumentation(BaseDocument document) {
        return repository.findAllAsBuiltDocumentation_Named(
                document.getUuid(),
                document.getEdition(),
                document.getSchemaVersion());
    }

}
