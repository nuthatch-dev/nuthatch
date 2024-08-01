package ru.nuthatch.generalworkjournal.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.nuthatch.generalworkjournal.common.BaseDocument;
import ru.nuthatch.generalworkjournal.dto.TitleChangeDto;
import ru.nuthatch.generalworkjournal.entity.GeneralWorkJournal;
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
}
