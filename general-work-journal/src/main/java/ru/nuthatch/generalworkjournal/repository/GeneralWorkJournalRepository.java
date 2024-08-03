package ru.nuthatch.generalworkjournal.repository;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.nuthatch.generalworkjournal.dto.TitleChangeDto;
import ru.nuthatch.generalworkjournal.entity.AsBuiltDocumentation;
import ru.nuthatch.generalworkjournal.entity.GeneralWorkJournal;
import ru.nuthatch.generalworkjournal.entity.SpecialJournal;
import ru.nuthatch.generalworkjournal.entity.WorksPerformingInfo;
import ru.nuthatch.generalworkjournal.entity.controlevent.ControlEventInfo;

import java.util.Collection;
import java.util.UUID;

@Repository
public interface GeneralWorkJournalRepository extends CommonRepository<GeneralWorkJournal> {

    // Получить все изменения титульного листа ОЖР
    @Query(nativeQuery = true)
    Collection<TitleChangeDto> findAllGeneralWorkJournalTitleChanges_Named(@Param(value = "uuid") UUID uuid);

    // Получение списка специальных журналов
    @Query(nativeQuery = true)
    Collection<SpecialJournal> findAllSpecialJournals_Named(@Param(value = "uuid") UUID uuid);

    // Получение списка сведений о выполненных работах
    @Query(nativeQuery = true)
    Collection<WorksPerformingInfo> findAllWorksPerformingInfos_Named(@Param(value = "uuid") UUID uuid);

    // Получение списка сведений о строительном контроле
    @Query(nativeQuery = true)
    Collection<ControlEventInfo> findAllControlEventInfos_Named(@Param(value = "uuid") UUID uuid);

    // Получение перечня исполнительной документации
    @Query(nativeQuery = true)
    Collection<AsBuiltDocumentation> findAllAsBuiltDocumentation_Named(
            @Param(value = "uuid") UUID uuid);

    @Query(value = "SELECT * FROM general_work_journal AS g WHERE g.archived = false",
            nativeQuery = true)
    Collection<GeneralWorkJournal> findAllNotArchived();

    @Query(value = "SELECT * FROM general_work_journal AS g WHERE g.archived = true",
            nativeQuery = true)
    Collection<GeneralWorkJournal> findAllArchived();

    // Изменить признак "в архиве" для ОЖР
    @Transactional
    @Modifying
    @Query(value = "UPDATE general_work_journal SET archived = :arch WHERE uuid = :uuid",
            nativeQuery = true)
    Boolean ChangeArchivedAttribute(@Param(value = "uuid") UUID uuid,
                                    @Param(value = "arch") Boolean archived);

}
