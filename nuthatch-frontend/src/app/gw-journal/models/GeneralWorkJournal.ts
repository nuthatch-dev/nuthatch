import {DocInfo} from "./DocInfo";
import {PermanentObjectInfo} from "./PermanentObjectInfo";
import {ConstructionTypeName} from "./ConstructionTypeName";
import {ExecutiveAuthorityDocInfo} from "./ExecutiveAuthorityDocInfo";
import {OrganizationWithOptionalSroAndId} from "./OrganizationWithOptionalSroAndId";
import {DesignerSupervisionRepresentativesSetItem} from "./DesignerSupervisionRepresentativesSetItem";
import {ProjectDocumentationExaminationDetails} from "./ProjectDocumentationExaminationDetails";
import {StateSupervisoryAuthorityInfo} from "./StateSupervisoryAuthorityInfo";
import {PermanentObjectCommonInfo} from "./PermanentObjectCommonInfo";
import {GeneralWorkJournalCommonInfo} from "./GeneralWorkJournalCommonInfo";
import {StateSupervisionInfo} from "./StateSupervisionInfo";
import {ExtraParameter} from "./ExtraParameter";

export interface GeneralWorkJournal {
    // Информация об UUID и редакции документа, UUID объекта капитального строительства, версии схемы
    uuid: string;
    schemaVersion: string;
    edition: number;

    // Идентификатор документа
    docInfo: DocInfo;

    // Объект капитального строительства
    permanentObjectInfo: PermanentObjectInfo;

    // Наименование вида строительства
    constructionTypeName: ConstructionTypeName;

    // Застройщик и его представители
    developerWithRepresentatives: string;

    // Лицо, ответственное за эксплуатацию здания/сооружения и его представители
    operatingPersonWithRepresentatives: string;

    // Региональный оператор и его представители
    regionalOperatorWithRepresentatives: string;

    // Технический заказчик и его представители
    technicalCustomerWithRepresentatives: string;

    // Сведения о разрешении на строительство
    permissionToConstructionRoot: ExecutiveAuthorityDocInfo;

    // Лицо, осуществляющее подготовку проектной документации
    projectDocumentationContractor: OrganizationWithOptionalSroAndId;

    /*
    Лица, осуществляющие подготовку проектной документации, выполняющие проверку
    соответствия выполняемых работ проектной документации (авторский надзор) и их представители (id)
     */
    designerSupervisionRepresentativesSet: DesignerSupervisionRepresentativesSetItem[];

    // Сведения о государственной экспертизе проектной документации
    projectDocumentationExaminationDetails: ProjectDocumentationExaminationDetails;

    // Лицо, осуществляющее строительство и его представители
    buildingContractorWithRepresentatives: string;

    // Другие лица, осуществляющие строительство, их уполномоченные представители
    otherDevelopersRepresentativesSet: string[];

    // Сведения о государственном строительном надзоре
    stateSupervisoryAuthorityInfo: StateSupervisoryAuthorityInfo;

    // Общие сведения об объекте капитального строительства
    permanentObjectCommonInfo: PermanentObjectCommonInfo;

    // Общие сведения об общем журнале работ
    generalWorkJournalCommonInfo: GeneralWorkJournalCommonInfo;

    // Регистрационная надпись органа государственного строительного надзора
    supervisoryAuthorityRegistrationMarkId: string;

    // Список ID инженерно-технического персонала лица, осуществляющего строительство
    engineeringAndTechnicalPersonsIdsSet: string[];

    // Сведения о государственном строительном надзоре при строительстве
    stateSupervisionInfo: StateSupervisionInfo;

    // Список дополнительных параметров
    extraParameterSet: ExtraParameter[];

    // Журнал находится в архиве
    archived: boolean;
}
