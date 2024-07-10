package ru.nuthatch.buildingobject.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Описание комплексного типа: PermanentObjectInfo
 * Объект капительного строительства
 */
@Data
@Entity
@Table(name = "permanent_object_info")
public class PermanentObjectInfo implements Serializable {
    @Id
    @GeneratedValue
    protected UUID uuid;

    /**
     * Наименование объекта (этапа)
     * Обязательный элемент
     * Минимум 1 символ
     */
    @Column(name = "permanent_object_name")
    protected String permanentObjectName;

    /**
     * Информация об адресе обьекта (Почтовом или строительном)
     * Обязательный элемент
     */
    @Embedded
    @Column(name = "permanent_object_address")
    protected PostalOrConstructionSiteAddress permanentObjectAddress;

    /**
     * Наименование вида строительства
     * Обязательный элемент
     * Наложенные ограничения –только одно из значений:
     * - строительство
     * - реконструкция
     * - капитальный ремонт
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "construction_type_name")
    protected ConstructionTypeName constructionTypeName;

    /**
     * Обязательный элемент (одно значение из элементов типа):
     * developerWithRepresentatives,
     * operatingPersonWithRepresentatives,
     * regionalOperatorWithRepresentatives
     * --
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

    /**
     * Технический заказчик и его представители
     * Необязательный элемент
     */
    @Column(name = "technical_customer_with_representatives")
    protected UUID technicalCustomerWithRepresentatives;

    /**
     * Лицо, осуществляющее подготовку проектной документации
     * Обязательный элемент
     */
    @Column(name = "project_documentation_contractor")
    protected UUID projectDocumentationContractor;

    /**
     * Лицо, осуществляющее строительство и его представители
     * Обязательный элемент
     */
    @Column(name = "building_contractor_with_representatives")
    protected UUID buildingContractorWithRepresentatives;

    /**
     * Другие лица, осуществляющие строительство, их уполномоченные представители
     * Необязательный элемент
     * Список
     */
    @ElementCollection
    @Column(name = "other_developers_representatives_list")
    protected List<UUID> otherDevelopersRepresentativesList = new ArrayList<>();

    /**
     * Сведения о государственном строительном надзоре
     * Обязательный элемент
     */
    @Column(name = "supervisory_authority")
    protected SupervisoryAuthority supervisoryAuthority;

    /**
     * Сведения о разрешении на строительство
     * Необязательный элемент
     */
    @Embedded
    @Column(name = "permission_to_construction_root")
    protected ExecutiveAuthorityDocInfo permissionToConstructionRoot;

    /**
     * Сведения о государственной экспертизе проектной документации в случаях, предусмотренных
     * статьей 49 Градостроительного кодекса Российской Федерации
     * Необязательный элемент
     */
    @Column(name = "projectDocumentation_examination_details")
    protected ProjectDocumentationExaminationDetails projectDocumentationExaminationDetails;

    /**
     * Общие сведения об объекте капитального строительства
     * Обязательный элемент
     */
    @Column(name = "permanent_object_common_info")
    protected PermanentObjectCommonInfo permanentObjectCommonInfo;
}
