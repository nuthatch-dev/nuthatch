package ru.nuthatch.generalworkjournal.repository;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.nuthatch.generalworkjournal.common.BaseDocument;
import ru.nuthatch.generalworkjournal.dto.TitleChangeDto;
import ru.nuthatch.generalworkjournal.entity.GeneralWorkJournal;

import java.util.Collection;
import java.util.UUID;

@Repository
public interface GeneralWorkJournalRepository extends JpaRepository<GeneralWorkJournal, BaseDocument> {

    // Получить все изменения титульного листа ОЖР
    @Query(nativeQuery = true)
    Collection<TitleChangeDto> findAllGeneralWorkJournalTitleChanges_Named(
            @Param(value = "uuid") UUID uuid,
            @Param(value = "ed") int edition,
            @Param(value = "ver") String version);

    @Query(value = "SELECT * FROM general_work_journal AS g WHERE g.archived = false",
            nativeQuery = true)
    Collection<GeneralWorkJournal> findAllNotArchived();

    @Query(value = "SELECT * FROM general_work_journal AS g WHERE g.archived = true",
            nativeQuery = true)
    Collection<GeneralWorkJournal> findAllArchived();

    // Изменить признак "в архиве" для ОЖР
    @Transactional
    @Modifying
    @Query(value = "UPDATE general_work_journal SET archived = :arch WHERE base_document_uuid = :uuid",
            nativeQuery = true)
    Boolean ChangeArchivedAttribute(@Param(value = "uuid") UUID uuid,
                                    @Param(value = "arch") Boolean archived);

}
