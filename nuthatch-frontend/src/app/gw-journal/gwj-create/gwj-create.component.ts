import {Component} from '@angular/core';
import {FormBuilder, FormGroup, FormsModule} from "@angular/forms";
import {GeneralWorkJournal} from "../models/GeneralWorkJournal";
import {ConstructionTypeName} from "../models/ConstructionTypeName";

@Component({
  selector: 'app-gwj-create',
  standalone: true,
  imports: [
    FormsModule
  ],
  templateUrl: './gwj-create.component.html',
  styleUrl: './gwj-create.component.css'
})
export class GwjCreateComponent {

  createForm: FormGroup;

  constructor(private fb: FormBuilder) {
    this.createForm = this.fb.group({

    });
  }

  createGeneralWorkJournal() {

  }

  /*
  ОЖР модель
   */

  private gwj: GeneralWorkJournal = {
    uuid: '',
    schemaVersion: '',
    edition: 1,
    docInfo: {
      name: '',
      number: '',
    },
    permanentObjectInfo: {
      permanentObjectName: '',
      permanentObjectAddress: {
        postalAddress: '',
        constructionSiteAddress: '',
      },
    },
    constructionTypeName: ConstructionTypeName.BUILDING,
    developerWithRepresentatives: '',
    operatingPersonWithRepresentatives: '',
    regionalOperatorWithRepresentatives: '',
    technicalCustomerWithRepresentatives: '',
    permissionToConstructionRoot: {
      uuid: '',
      docInfo: {
        name: '',
        number: '',
      },
      expirationDate: new Date(), // TODO: ???????
      docChangeDate: new Date(), // TODO: ???????
      executiveAuthorityId: '',
      executiveAuthorityTitle: '',
    },
    projectDocumentationContractor: {
      uuid: '',
      legalEntity: '',
      individualEntrepreneur: '',
      sro: '',
    },
    designerSupervisionRepresentativesSet: [],
    projectDocumentationExaminationDetails: {
      sequenceNumber: -1,
      expertiseConclusionRequisites: '',
      executiveAuthorityName: '',
    },
    buildingContractorWithRepresentatives: '',
    otherDevelopersRepresentativesSet: [],
    stateSupervisoryAuthorityInfo: {
      uuid: '',
      supervisoryAuthority: '',
      supervisoryAuthorityOfficialPerson: {
        uuid: '',
        representative: '',
        administrativeDocument: '',
      }
    },
    permanentObjectCommonInfo: {
      projectCharacteristics: '',
      constructionStartDate: new Date(), // TODO: ???????
      constructionEndDate: new Date(), // TODO: ???????
    },
    generalWorkJournalCommonInfo: {
      uuid: '',
      journalVolumeValue: -1,
      journalVolumeUnit: '',
      beginDate: new Date(), // TODO: ???????
      endDate: new Date(), // TODO: ???????
      developerRepresentative: '',
    },
    supervisoryAuthorityRegistrationMarkId: '',
    engineeringAndTechnicalPersonsIdsSet: [],
    stateSupervisionInfo: {
      uuid: '',
      stateSupervisionRecordsIdsSet: [],
    },
    extraParameterSet: [],
    archived: false,
  }


}
