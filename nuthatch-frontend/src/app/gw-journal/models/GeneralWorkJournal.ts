import {DocInfo} from "./DocInfo";
import {PermanentObjectInfo} from "./PermanentObjectInfo";
import {ConstructionTypeName} from "./ConstructionTypeName";
import {ExecutiveAuthorityDocInfo} from "./ExecutiveAuthorityDocInfo";
import {OrganizationWithOptionalSro} from "./OrganizationWithOptionalSro";
import {DesignerSupervisionRepresentativesSetItem} from "./DesignerSupervisionRepresentativesSetItem";
import {ProjectDocumentationExaminationDetails} from "./ProjectDocumentationExaminationDetails";
import {StateSupervisoryAuthorityInfo} from "./StateSupervisoryAuthorityInfo";
import {PermanentObjectCommonInfo} from "./PermanentObjectCommonInfo";
import {GeneralWorkJournalCommonInfo} from "./GeneralWorkJournalCommonInfo";
import {StateSupervisionInfo} from "./StateSupervisionInfo";
import {ExtraParameter} from "./ExtraParameter";
import {
  IndividualEntrepreneurOrLegalEntityOrIndividualAndId
} from "./IndividualEntrepreneurOrLegalEntityOrIndividualAndId";
import {OrganizationWithOptionalSroAndId} from "./OrganizationWithOptionalSroAndId";

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
  constructionTypeName: ConstructionTypeName | null;

  // Застройщик и его представители
  developer: IndividualEntrepreneurOrLegalEntityOrIndividualAndId | null;
  developerRepresentativeSet: string[];

  // Лицо, ответственное за эксплуатацию здания/сооружения и его представители
  operatingPerson: IndividualEntrepreneurOrLegalEntityOrIndividualAndId | null;
  operatingPersonRepresentativeSet: string[];

  // Региональный оператор и его представители
  regionalOperator: OrganizationWithOptionalSroAndId | null;
  regionalOperatorRepresentativeSet: string[];

  // Технический заказчик и его представители
  technicalCustomer: OrganizationWithOptionalSroAndId | null;
  technicalCustomerRepresentativeSet: string[];

  // Сведения о разрешении на строительство
  permissionToConstructionRoot: ExecutiveAuthorityDocInfo | null;

  // Лицо, осуществляющее подготовку проектной документации
  projectDocumentationContractor: OrganizationWithOptionalSro | null;

  /*
  Лица, осуществляющие подготовку проектной документации, выполняющие проверку
  соответствия выполняемых работ проектной документации (авторский надзор) и их представители (id)
   */
  designerSupervisionRepresentativesSet: DesignerSupervisionRepresentativesSetItem[];

  // Сведения о государственной экспертизе проектной документации
  projectDocumentationExaminationDetails: ProjectDocumentationExaminationDetails | null;

  // Лицо, осуществляющее строительство и его представители
  buildingContractorWithRepresentatives: string;

  // Другие лица, осуществляющие строительство, их уполномоченные представители
  otherDevelopersRepresentativesSet: string[];

  // Сведения о государственном строительном надзоре
  stateSupervisoryAuthorityInfo: StateSupervisoryAuthorityInfo | null;

  // Общие сведения об объекте капитального строительства
  permanentObjectCommonInfo: PermanentObjectCommonInfo | null;

  // Общие сведения об общем журнале работ
  generalWorkJournalCommonInfo: GeneralWorkJournalCommonInfo | null;

  // Регистрационная надпись органа государственного строительного надзора
  supervisoryAuthorityRegistrationMarkId: string;

  // Список ID инженерно-технического персонала лица, осуществляющего строительство
  engineeringAndTechnicalPersonsIdsSet: string[];

  // Сведения о государственном строительном надзоре при строительстве
  stateSupervisionInfo: StateSupervisionInfo | null;

  // Список дополнительных параметров
  extraParameterSet: ExtraParameter[];

  // Журнал находится в архиве
  archived: boolean;
}
