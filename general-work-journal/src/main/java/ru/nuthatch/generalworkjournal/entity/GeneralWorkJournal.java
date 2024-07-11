package ru.nuthatch.generalworkjournal.entity;

import jakarta.persistence.*;
import lombok.Data;
import ru.nuthatch.generalworkjournal.common.BaseDocument;
import ru.nuthatch.generalworkjournal.common.ConstructionTypeName;
import ru.nuthatch.generalworkjournal.common.OrganizationWithOptionalSroAndId;

import java.io.Serial;
import java.io.Serializable;
import java.util.*;

/**
 * Описание комплексного типа: generalWorkJournal
 * Общий журнал работ
 */
@Data
@Entity
@Table(name = "general_work_journal")
public class GeneralWorkJournal implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * Информация об UUID и редакции документа, UUID объекта капитального строительства, версии схемы
     * Обязательный элемент
     */
    @EmbeddedId
    protected BaseDocument baseDocument;

    /**
     * Идентификатор документа
     * Обязательный элемент
     */
    @Embedded
    protected DocInfo docInfo;

    /**
     * Объект капитального строительства
     * Обязательный элемент
     */
    @ManyToOne
    @Column(name = "permanent_object_info",
            nullable = false)
    protected PermanentObjectInfo permanentObjectInfo;

    /**
     * Наименование вида строительства
     * Обязательный элемент
     * Наложенные ограничения – только одно из значений:
     * - строительство
     * - реконструкция
     * - капитальный ремонт
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "construction_type_name",
            nullable = false)
    protected ConstructionTypeName constructionTypeName;

    /**
     * Обязательный элемент (одно значение из элементов типа):
     * developerWithRepresentatives,
     * operatingPersonWithRepresentatives,
     * regionalOperatorWithRepresentatives
     * --------------------------------------------------
     * Застройщик и его представители
     */
    @Column(name = "developer_with_representatives")
    protected UUID developerWithRepresentatives;
    /**
     * Лицо, ответственное за эксплуатацию здания/сооружения и его представители
     */
    @Column(name = "operating_person_with_representatives")
    protected UUID operatingPersonWithRepresentatives;
    /**
     * Региональный оператор и его представители
     */
    @Column(name = "regional_operator_with_representatives")
    protected UUID regionalOperatorWithRepresentatives;
    // --------------------------------------------------

    /**
     * Технический заказчик и его представители
     * Необязательный элемент
     */
    @Column(name = "technical_customer_with_representatives")
    protected UUID technicalCustomerWithRepresentatives;

    /**
     * Сведения о разрешении на строительство
     * Необязательный элемент
     */
    @ManyToOne
    @Column(name = "permission_to_construction_root")
    protected ExecutiveAuthorityDocInfo permissionToConstructionRoot;

    /**
     * Лицо, осуществляющее подготовку проектной документации
     * Обязательный элемент
     */
    @ManyToOne
    @Column(name = "project_documentation_contractor",
            nullable = false)
    protected OrganizationWithOptionalSroAndId projectDocumentationContractor;

    /**
     * Лица, осуществляющие подготовку проектной документации, выполняющие проверку
     * соответствия выполняемых работ проектной документации (авторский надзор) и их представители (id)
     * Необязательный элемент
     * Список
     */
    @ManyToMany(fetch = FetchType.LAZY)
    @Column(name = "designer_supervision_representatives_set")
    protected Set<DesignerSupervisionRepresentativesSetItem> designerSupervisionRepresentativesSet = new HashSet<>();

    /**
     * Сведения о государственной экспертизе проектной документации в случаях,
     * предусмотренных статьей 49 Градостроительного кодекса Российской Федерации
     * Необязательный элемент
     */
    @Column(name = "projectDocumentation_examination_details")
    protected ProjectDocumentationExaminationDetails projectDocumentationExaminationDetails;

    /**
     * Лицо, осуществляющее строительство и его представители
     * Обязательный элемент
     */
    @Column(name = "building_contractor_with_representatives",
            nullable = false)
    protected UUID buildingContractorWithRepresentatives;

    /**
     * Другие лица, осуществляющие строительство, их уполномоченные представители
     * Необязательный элемент
     * Список
     */
    @ElementCollection
    @Column(name = "other_developers_representatives_set")
    protected Set<UUID> otherDevelopersRepresentativesSet = new HashSet<>();

    /**
     * Сведения о государственном строительном надзоре
     * Необязательный элемент
     */
    @ManyToOne
    @Column(name = "state_supervisory_authority_info")
    protected StateSupervisoryAuthorityInfo stateSupervisoryAuthorityInfo;

    /**
     * Общие сведения об объекте капитального строительства
     * Обязательный элемент
     */
    @Embedded
    protected PermanentObjectCommonInfo permanentObjectCommonInfo;

    /**
     * Общие сведения об общем журнале работ
     * Необязательный элемент
     */
    @OneToOne
    @Column(name = "general_work_journal_common_info")
    protected GeneralWorkJournalCommonInfo generalWorkJournalCommonInfo;

    /**
     * Регистрационная надпись органа государственного строительного надзора (Id)
     * Необязательный элемент
     * Строгий формат:
     * _хххххххх-хххх-хххх-хххх-хххххххххххх
     * Наложенные ограничения
     * _[0-9a-f]{8}-[0-9a-f]{4}-[0-5][0-9a-f]{3}-[089ab][0-9a-f]{3}-[0-9a-f]{12}
     */
    @Column(name = "supervisory_authority_registration_mark_id")
    protected UUID supervisoryAuthorityRegistrationMarkId;

    /**
     * Список ID инженерно-технического персонала лица, осуществляющего строительство,
     * занятого при строительстве, реконструкции,
     * капитальном ремонте объекта капитального строительства
     * Обязательный элемент
     * Строгий формат:
     * _хххххххх-хххх-хххх-хххх-хххххххххххх
     * Наложенные ограничения
     * _[0-9a-f]{8}-[0-9a-f]{4}-[0-5][0-9a-f]{3}-[089ab][0-9a-f]{3}-[0-9a-f]{12}
     * Список
     */
    @ElementCollection
    @Column(name = "engineering_and_technical_persons_ids_set")
    protected Set<UUID> engineeringAndTechnicalPersonsIdsSet = new HashSet<>();


}
