package ru.nuthatch.generalworkjournal.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import ru.nuthatch.generalworkjournal.common.*;
import ru.nuthatch.generalworkjournal.dto.TitleChangeDto;
import ru.nuthatch.generalworkjournal.entity.controlevent.ControlEventInfo;
import ru.nuthatch.generalworkjournal.entity.representative.IndividualEntrepreneurOrLegalEntityOrIndividualAndId;
import ru.nuthatch.generalworkjournal.entity.representative.OrganizationWithOptionalSro;

import java.io.Serializable;
import java.util.*;

/**
 * Описание комплексного типа: generalWorkJournal
 * Общий журнал работ
 */

@NamedNativeQueries({
        /*
        Запрос на получение изменений титульного листа ОЖР
         */
        @NamedNativeQuery(name = "GeneralWorkJournal.findAllGeneralWorkJournalTitleChanges_Named",
                query = "SELECT uuid, change_date, change_description_with_basis_basis, " +
                        "change_description_with_basis_description, responsible_representative, sequence_number " +
                        "FROM general_work_journal_title_change " +
                        "WHERE general_work_journal_uuid = :uuid",
                resultSetMapping = "Mapping.TitleChangeDto"),

        /*
        Запрос на получение списка специальных журналов
         */
        @NamedNativeQuery(name = "GeneralWorkJournal.findAllSpecialJournals_Named",
                query = "SELECT * FROM special_journal " +
                        "WHERE general_work_journal_uuid = :uuid",
                resultClass = SpecialJournal.class),

        /*
        Запрос на получение списка сведений о выполненных работах
         */
        @NamedNativeQuery(name = "GeneralWorkJournal.findAllWorksPerformingInfos_Named",
                query = "SELECT * FROM works_performing_info " +
                        "WHERE general_work_journal_uuid = :uuid",
                resultClass = WorksPerformingInfo.class),

        /*
        Запрос сведений о строительном контроле
         */
        @NamedNativeQuery(name = "GeneralWorkJournal.findAllControlEventInfos_Named",
                query = "SELECT * FROM control_event_info " +
                        "WHERE general_work_journal_uuid = :uuid",
                resultClass = ControlEventInfo.class),

        /*
        Запрос на получение перечня исполнительной документации
         */
        @NamedNativeQuery(name = "GeneralWorkJournal.findAllAsBuiltDocumentation_Named",
                query = "SELECT * FROM as_built_documentation " +
                        "WHERE general_work_journal_uuid = :uuid",
                resultClass = AsBuiltDocumentation.class)

})

@SqlResultSetMapping(name = "Mapping.TitleChangingDto",
        classes = @ConstructorResult(targetClass = TitleChangeDto.class,
                columns = {
                        @ColumnResult(name = "uuid"),
                        @ColumnResult(name = "sequence_number", type = Integer.class),
                        @ColumnResult(name = "change_date", type = Date.class),
                        @ColumnResult(name = "change_description_with_basis_basis"),
                        @ColumnResult(name = "change_description_with_basis_description"),
                        @ColumnResult(name = "responsible_representative")
                }))

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "general_work_journal")
public class GeneralWorkJournal extends CommonEntity implements Serializable {

    /**
     * Версия схемы
     * Обязательный элемент, заполняется шиной
     */
    @Column(nullable = false,
            updatable = false)
    protected String schemaVersion;

    /**
     * Редакция документа (версия)
     * Обязательный элемент, заполняется ИС
     */
    @Column(nullable = false,
            updatable = false)
    protected int edition;

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
    @OneToOne
    protected IndividualEntrepreneurOrLegalEntityOrIndividualAndId developer;
    @ElementCollection
    protected Set<UUID> developerRepresentativeSet = new HashSet<>();
    /**
     * Лицо, ответственное за эксплуатацию здания/сооружения и его представители
     */
    @OneToOne
    protected IndividualEntrepreneurOrLegalEntityOrIndividualAndId operatingPerson;
    @ElementCollection
    protected Set<UUID> operatingPersonRepresentativeSet = new HashSet<>();
    /**
     * Региональный оператор и его представители
     */
    @OneToOne(cascade = CascadeType.ALL)
    protected OrganizationWithOptionalSro regionalOperator;
    @ElementCollection
    protected Set<UUID> regionalOperatorRepresentativeSet = new HashSet<>();
    // --------------------------------------------------

    /**
     * Технический заказчик и его представители
     * Необязательный элемент
     */
    @OneToOne(cascade = CascadeType.ALL)
    protected OrganizationWithOptionalSro technicalCustomer;
    @ElementCollection
    protected Set<UUID> technicalCustomerRepresentativeSet = new HashSet<>();

    /**
     * Сведения о разрешении на строительство
     * Необязательный элемент
     */
    @ManyToOne
    protected ExecutiveAuthorityDocInfo permissionToConstructionRoot;

    /**
     * Лицо, осуществляющее подготовку проектной документации
     * Обязательный элемент
     */
    @OneToOne(cascade = CascadeType.ALL)
    protected OrganizationWithOptionalSro projectDocumentationContractor;

    /**
     * Лица, осуществляющие подготовку проектной документации, выполняющие проверку
     * соответствия выполняемых работ проектной документации (авторский надзор) и их представители (id)
     * Необязательный элемент
     * Список
     */
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "general_journal_designer_representative")
    protected Set<DesignerSupervisionRepresentativesSetItem> designerSupervisionRepresentativesSet = new HashSet<>();

    /**
     * Сведения о государственной экспертизе проектной документации в случаях,
     * предусмотренных статьей 49 Градостроительного кодекса Российской Федерации
     * Необязательный элемент
     */
    @Column(name = "project_documentation_examination_details")
    protected ProjectDocumentationExaminationDetails projectDocumentationExaminationDetails;

    /**
     * Лицо, осуществляющее строительство и его представители
     * Обязательный элемент
     */
    @OneToOne(cascade = CascadeType.ALL)
    protected OrganizationWithOptionalSro buildingContractor;
    @ElementCollection
    protected Set<UUID> buildingContractorRepresentativeSet = new HashSet<>();

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

    /*
    Сведения об изменениях в записях Титульного листа общего журнала работ
    Необязательный элемент
    Список generalWorkJournalTitleChange (native query)
     */

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

    /*
    Перечень специальных журналов, в которых ведется учет выполнения работ,
    а также журналов авторского надзора лица, осуществляющего подготовку проектной документации
    Обязательный элемент
    Список specialJournal (native query)
     */

    /*
    Сведения о выполнении работ в процессе строительства, реконструкции,
    капитального ремонта объекта капитального строительства (id)
    Обязательный элемент
    Список worksPerformingInfo (native query)
     */

    /*
    Сведения о строительном контроле в процессе строительства, реконструкции,
    капитального ремонта объекта капитального строительства (список)
    Необязательный элемент
    Список controlEventInfo (native query)
     */

    /*
    Сведения о строительном контроле лица, осуществляющего строительство,
    в процессе строительства, реконструкции, капитального ремонта объекта
    капитального строительства (список)
    Необязательный элемент
    Список controlEventInfo (native query)
    TODO: Списки разные!!!
     */

    /*
    Перечень исполнительной документации при строительстве, реконструкции,
    капитальном ремонте объекта капитального строительства
    Обязательный элемент
    Список asBuiltDocumentation (native query)
     */

    /**
     * Сведения о государственном строительном надзоре при строительстве,
     * реконструкции, капитальном ремонте объекта капитального строительства
     * Необязательный элемент
     */
    @OneToOne
    protected StateSupervisionInfo stateSupervisionInfo;

    /**
     * Список дополнительных параметров
     * Необязательный элемент
     * Список
     */
    @ManyToMany
    @JoinTable(name = "general_journal_extra_parameter")
    protected Set<ExtraParameter> extraParameterSet = new HashSet<>();

    /**
     * Журнал находится в архиве
     */
    protected boolean archived = false;
}
