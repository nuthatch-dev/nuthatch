package ru.nuthatch.generalworkjournal.service;

import org.springframework.stereotype.Service;
import ru.nuthatch.generalworkjournal.dto.TitleChangeDto;
import ru.nuthatch.generalworkjournal.entity.AsBuiltDocumentation;
import ru.nuthatch.generalworkjournal.entity.GeneralWorkJournal;
import ru.nuthatch.generalworkjournal.entity.SpecialJournal;
import ru.nuthatch.generalworkjournal.entity.WorksPerformingInfo;
import ru.nuthatch.generalworkjournal.entity.controlevent.ControlEventInfo;
import ru.nuthatch.generalworkjournal.repository.GeneralWorkJournalRepository;

import java.util.Collection;
import java.util.UUID;

@Service
public class GeneralWorkJournalService extends CommonService<GeneralWorkJournal, GeneralWorkJournalRepository> {

    public GeneralWorkJournalService(GeneralWorkJournalRepository repository) {
        super(repository);
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
    public Boolean ChangeArchivedAttribute(UUID uuid) {
        return repository
                .findById(uuid)
                .map(value -> repository.ChangeArchivedAttribute(
                                value.getUuid(),
                                !value.isArchived()
                        )
                )
                .orElseGet(() -> null);
    }

    // Получить все изменения титульного листа ОЖР
    public Collection<TitleChangeDto> findTitleChanges(UUID uuid) {
        return repository.findAllGeneralWorkJournalTitleChanges_Named(uuid);
    }

    // Получение списка специальных журналов
    public Collection<SpecialJournal> findSpecialJournals(UUID uuid) {
        return repository.findAllSpecialJournals_Named(uuid);
    }

    // Получение списка сведений о выполненных работах
    public Collection<WorksPerformingInfo> findWorksPerformingInfos(UUID uuid) {
        return repository.findAllWorksPerformingInfos_Named(uuid);
    }

    // Получение списка сведений о строительном контроле
    public Collection<ControlEventInfo> findControlEventInfos(UUID uuid) {
        return repository.findAllControlEventInfos_Named(uuid);
    }

    // Получение перечня исполнительной документации
    public Collection<AsBuiltDocumentation> findAsBuiltDocumentation(UUID uuid) {
        return repository.findAllAsBuiltDocumentation_Named(uuid);
    }

}
