import {Component} from '@angular/core';
import {FormBuilder, FormGroup, FormsModule, ReactiveFormsModule, Validators} from "@angular/forms";
import {GeneralWorkJournal} from "../models/GeneralWorkJournal";
import {ConstructionTypeName} from "../models/ConstructionTypeName";
import {GwJournalService} from "../gw-journal.service";
import {Router} from "@angular/router";
import {KeyValuePipe, NgForOf, NgIf} from "@angular/common";
import {
  IndividualEntrepreneurOrLegalEntityOrIndividualAndId
} from "../models/IndividualEntrepreneurOrLegalEntityOrIndividualAndId";
import {DeveloperRepresentativeComponent} from "./developer-representative/developer-representative.component";
import {SelectDeveloper} from "./common/select-counterparty/SelectDeveloper";
import {SelectOperatingPerson} from "./common/select-counterparty/SelectOperatingPerson";
import {OrganizationWithOptionalSro} from "../../models/representative/OrganizationWithOptionalSro";
import {SelectTechnicalCustomer} from "./common/select-counterparty/SelectTechnicalCustomer";
import {SelectRegionalOperator} from "./common/select-counterparty/SelectRegionalOperator";
import {DeveloperChoice} from "./common/select-counterparties/developer-choice";
import {TechnicalCustomerChoice} from "./common/select-counterparties/technical-customer-choice";
import {OperationPersonChoice} from "./common/select-counterparties/operation-person-choice";
import {RegionalOperatorChoice} from "./common/select-counterparties/regional-operator-choice";
import {
  ProjectDocumentationContractorChoice
} from "./common/select-counterparties/project-documentation-contractor-choice";
import {BuildingContractorChoice} from "./common/select-counterparties/building-contractor-choice";

@Component({
  selector: 'app-gwj-create',
  standalone: true,
  imports: [
    FormsModule,
    ReactiveFormsModule,
    NgIf,
    NgForOf,
    KeyValuePipe,
    DeveloperRepresentativeComponent,
    SelectDeveloper,
    SelectOperatingPerson,
    SelectTechnicalCustomer,
    SelectRegionalOperator,
    DeveloperChoice,
    TechnicalCustomerChoice,
    OperationPersonChoice,
    RegionalOperatorChoice,
    ProjectDocumentationContractorChoice,
    BuildingContractorChoice,
  ],
  templateUrl: './gwj-create.component.html',
  styleUrl: './gwj-create.component.css'
})
export class GwjCreateComponent {

  createForm: FormGroup;
  submitted: boolean = false;
  constructionTypes: Array<string> = Object.keys(ConstructionTypeName).filter(key => isNaN(+key));

  constructor(private fb: FormBuilder,
              private service: GwJournalService,
              private router: Router) {

    this.createForm = this.fb.group({
      schemaVersion: [''],
      edition: [null],
      gwj_DocInfoName: [''],
      gwj_DocInfoNumber: ['', Validators.required, Validators.min(1)],
      permanentObjectName: ['', Validators.required, Validators.minLength(1)],
      postalAddress: [''],
      constructionSiteAddress: [''],
      constructionTypeName: [null, Validators.required],
      permission_DocInfoName: [''],
      permission_DocInfoNumber: [''],
      permission_ExpirationDate: [null],
      permission_DocChangeDate: [null],
      permission_ExecutiveAuthorityId: [''],
      permission_ExecutiveAuthorityTitle: [''],
      designerSupervisionRepresentativesSet: [null],
      projectExamination_SequenceNumber: [null],
      projectExamination_Requisites: [''],
      projectExamination_AuthorityName: [''],
      otherDevelopersRepresentativesSet: [null],
      supervisoryAuthority: [''],
      supervisory_Representative: [''],
      supervisory_AdministrativeDocument: [''],
      projectCharacteristics: [''],
      constructionStartDate: [null],
      constructionEndDate: [null],
      commonInfo_JournalVolumeValue: [null],
      commonInfo_JournalVolumeUnit: [''],
      commonInfo_BeginDate: [null],
      commonInfo_EndDate: [null],
      commonInfo_DeveloperRepresentative: [''],
      supervisoryAuthorityRegistrationMarkId: [''],
      engineeringAndTechnicalPersonsIdsSet: [null],
      stateSupervisionRecordsIdsSet: [null],
      extraParameterSet: [null],
    });
  }

  get f() {
    return this.createForm.controls;
  }

  onSubmit() {
    this.submitted = true;
    if (this.createForm.invalid) {
      return;
    }

    let gwj: GeneralWorkJournal = {
      uuid: '',
      schemaVersion: this.f['schemaVersion'].value,
      edition: this.f['edition'].value,
      docInfo: {
        name: this.f['gwj_DocInfoName'].value,
        number: this.f['gwj_DocInfoNumber'].value,
      },
      permanentObjectInfo: {
        permanentObjectName: this.f['permanentObjectName'].value,
        permanentObjectAddress: {
          postalAddress: this.f['postalAddress'].value,
          constructionSiteAddress: this.f['constructionSiteAddress'].value,
        },
      },
      constructionTypeName: this.f['constructionTypeName'].value,
      developer: this.createdGeneralWorkJournal.developer,
      developerRepresentativeSet: [],
      operatingPerson: this.createdGeneralWorkJournal.operatingPerson,
      operatingPersonRepresentativeSet: [],
      regionalOperator: this.createdGeneralWorkJournal.regionalOperator,
      regionalOperatorRepresentativeSet: [],
      technicalCustomer: this.createdGeneralWorkJournal.technicalCustomer,
      technicalCustomerRepresentativeSet: [],
      permissionToConstructionRoot: {
        uuid: '',
        docInfo: {
          name: this.f['permission_DocInfoName'].value,
          number: this.f['permission_DocInfoNumber'].value,
        },
        expirationDate: this.f['permission_ExpirationDate'].value,
        docChangeDate: this.f['permission_DocChangeDate'].value,
        executiveAuthorityId: this.f['permission_ExecutiveAuthorityId'].value,
        executiveAuthorityTitle: this.f['permission_ExecutiveAuthorityTitle'].value,
      },
      projectDocumentationContractor: this.createdGeneralWorkJournal.projectDocumentationContractor,
      designerSupervisionRepresentativesSet: this.f['designerSupervisionRepresentativesSet'].value,
      projectDocumentationExaminationDetails: {
        sequenceNumber: this.f['projectExamination_SequenceNumber'].value,
        expertiseConclusionRequisites: this.f['projectExamination_Requisites'].value,
        executiveAuthorityName: this.f['projectExamination_AuthorityName'].value,
      },
      buildingContractor: this.createdGeneralWorkJournal.buildingContractor,
      buildingContractorRepresentativeSet: this.createdGeneralWorkJournal.buildingContractorRepresentativeSet,
      otherDevelopersRepresentativesSet: this.f['otherDevelopersRepresentativesSet'].value,
      stateSupervisoryAuthorityInfo: {
        uuid: '',
        supervisoryAuthority: this.f['supervisoryAuthority'].value,
        supervisoryAuthorityOfficialPerson: {
          uuid: '',
          representative: this.f['supervisory_Representative'].value,
          administrativeDocument: this.f['supervisory_AdministrativeDocument'].value,
        }
      },
      permanentObjectCommonInfo: {
        projectCharacteristics: this.f['projectCharacteristics'].value,
        constructionStartDate: this.f['constructionStartDate'].value,
        constructionEndDate: this.f['constructionEndDate'].value,
      },
      generalWorkJournalCommonInfo: {
        uuid: '',
        journalVolumeValue: this.f['commonInfo_JournalVolumeValue'].value,
        journalVolumeUnit: this.f['commonInfo_JournalVolumeUnit'].value,
        beginDate: this.f['commonInfo_BeginDate'].value,
        endDate: this.f['commonInfo_EndDate'].value,
        developerRepresentative: this.f['commonInfo_DeveloperRepresentative'].value,
      },
      supervisoryAuthorityRegistrationMarkId: this.f['supervisoryAuthorityRegistrationMarkId'].value,
      engineeringAndTechnicalPersonsIdsSet: this.f['engineeringAndTechnicalPersonsIdsSet'].value,
      stateSupervisionInfo: {
        uuid: '',
        stateSupervisionRecordsIdsSet: this.f['stateSupervisionRecordsIdsSet'].value,
      },
      extraParameterSet: this.f['extraParameterSet'].value,
      archived: false,
    }

    this.service.createGeneralWorkJournal(gwj).subscribe({
      next: value => {
        this.router.navigate(['/gwj-details', value.uuid]);
      },
      error: err => console.log(err),
      complete: () => console.info('complete')
    });
  }

  /*
  Выбор застройщика, передача Id застройщика для выбора представителей
   */
  developerId: string = "";

  setDeveloperId(id: string) {
    this.developerId = id;
  }

  onDeveloperSelected(developer: IndividualEntrepreneurOrLegalEntityOrIndividualAndId) {
    this.createdGeneralWorkJournal.developer = developer;
  }

  onDeveloperRepresentativeSelected(representativeIdSet: string[]) {
    this.createdGeneralWorkJournal.developerRepresentativeSet = representativeIdSet;
  }

  /*
  Выбор лица, ответственного за эксплуатацию
   */
  operatingPersonId: string = "";

  setOperatingPersonId(id: string) {
    this.operatingPersonId = id;
  }

  onOperatingPersonSelected(op: IndividualEntrepreneurOrLegalEntityOrIndividualAndId) {
    this.createdGeneralWorkJournal.operatingPerson = op;
  }

  /*
  Выбор регионального оператора
   */
  regionalOperatorId: string = "";

  setRegionalOperatorId(id: string) {
    this.regionalOperatorId = id;
  }

  onRegionalOperatorSelected(ro: OrganizationWithOptionalSro) {
    this.createdGeneralWorkJournal.regionalOperator = ro;
  }

  /*
  Выбор технического заказчика
   */
  technicalCustomerId: string = "";

  setTechnicalCustomerId(id: string) {
    this.technicalCustomerId = id;
  }

  onTechnicalCustomerSelected(tc: OrganizationWithOptionalSro) {
    this.createdGeneralWorkJournal.technicalCustomer = tc;
  }

  /*
  Выбор лица, осуществляющего подготовку проектной документации
   */
  projectDocumentationContractorId: string = "";

  setProjectDocumentationContractorId(id: string) {
    this.projectDocumentationContractorId = id;
  }

  onProjectDocumentationContractorSelected(pdc: OrganizationWithOptionalSro) {
    this.createdGeneralWorkJournal.projectDocumentationContractor = pdc;
  }

  /*
  Выбор лица, осуществляющего строительство
   */
  buildingContractorId: string = "";

  setBuildingContractorId(id: string) {
    this.buildingContractorId = id;
  }

  onBuildingContractorSelected(bc: OrganizationWithOptionalSro) {
    this.createdGeneralWorkJournal.buildingContractor = bc;
  }

  createdGeneralWorkJournal: GeneralWorkJournal = {
    uuid: "",
    schemaVersion: "",
    edition: 0,
    docInfo: {
      name: "",
      number: "",
    },
    permanentObjectInfo: {
      permanentObjectName: "",
      permanentObjectAddress: {
        postalAddress: "",
        constructionSiteAddress: "",
      },
    },
    constructionTypeName: null,
    developer: null,
    developerRepresentativeSet: [],
    operatingPerson:  null,
    operatingPersonRepresentativeSet: [],
    regionalOperator: null,
    regionalOperatorRepresentativeSet: [],
    technicalCustomer: null,
    technicalCustomerRepresentativeSet: [],
    permissionToConstructionRoot: null,
    projectDocumentationContractor: null,
    designerSupervisionRepresentativesSet: [],
    projectDocumentationExaminationDetails: null,
    buildingContractor: null,
    buildingContractorRepresentativeSet: [],
    otherDevelopersRepresentativesSet: [],
    stateSupervisoryAuthorityInfo: null,
    permanentObjectCommonInfo: null,
    generalWorkJournalCommonInfo: null,
    supervisoryAuthorityRegistrationMarkId: "",
    engineeringAndTechnicalPersonsIdsSet: [],
    stateSupervisionInfo: null,
    extraParameterSet: [],
    archived: false,
  };
}
