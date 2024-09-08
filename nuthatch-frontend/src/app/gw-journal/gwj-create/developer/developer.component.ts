import {Component, EventEmitter, OnInit, Output} from '@angular/core';
import {Individual} from "../../../models/representative/Individual";
import {
  IndividualEntrepreneur
} from "../../../models/representative/IndividualEntrepreneur";
import {LegalEntity} from "../../../models/representative/LegalEntity";
import {CounterpartiesService} from "../common/counterparties.service";
import {NgIf} from "@angular/common";
import {ReactiveFormsModule} from "@angular/forms";
import {CounterpartyType} from "../common/counterparty-type";
import {CreateCounterpartyComponent} from "../common/create-counterparty/create-counterparty.component";
import {
  IndividualEntrepreneurOrLegalEntityOrIndividualAndId
} from "../../models/IndividualEntrepreneurOrLegalEntityOrIndividualAndId";

@Component({
  selector: 'app-select-developer',
  standalone: true,
  imports: [
    NgIf,
    ReactiveFormsModule,
    CreateCounterpartyComponent
  ],
  templateUrl: './developer.component.html',
  styleUrl: './developer.component.css'
})
export class DeveloperComponent implements OnInit {

  individualList: Individual[] = [];
  individualEntrepreneurList: IndividualEntrepreneur[] = [];
  legalEntityList: LegalEntity[] = [];
  protected readonly CounterpartyType = CounterpartyType;

  @Output() onCounterpartySelected =
    new EventEmitter<IndividualEntrepreneurOrLegalEntityOrIndividualAndId>();

  counterpartySelected(counterparty: IndividualEntrepreneurOrLegalEntityOrIndividualAndId) {
    this.onCounterpartySelected.emit(counterparty);
    this.developerAsideHidden = true;
  }

  constructor(private service: CounterpartiesService) {
  }

  ngOnInit(): void {
    this.getIndividualList();
    this.getIndividualEntrepreneurList();
    this.getLegalEntityList();
  }

  selectedCounterparty: IndividualEntrepreneurOrLegalEntityOrIndividualAndId | undefined;

  dropCounterparty() {
    this.selectedCounterparty = {
      uuid: "",
      organizationWithOptionalSro: {
        legalEntity: null,
        individualEntrepreneur: null,
      },
      individual: null,
    };
  }

  /*
  Отображение выбранного контрагента в качестве застройщика, отображение СРО (при наличии).
  Передача контрагента в виде IndividualEntrepreneurOrLegalEntityOrIndividualAndId
  для сохранения в ОЖР
   */
  displayDeveloperName: string = " ";
  displayDeveloperSro: string = "н/у";

  individualSelected(entity: Individual) {
    this.dropCounterparty();
    this.selectedCounterparty!.individual = entity;
    this.displayDeveloperName = this.individualToString(entity);
    this.displayDeveloperSro = "н/у";
    this.counterpartySelected(this.selectedCounterparty!)
  }

  individualEntrepreneurSelected(entity: IndividualEntrepreneur) {
    this.dropCounterparty();
    this.selectedCounterparty!.organizationWithOptionalSro!.individualEntrepreneur = entity;
    this.displayDeveloperName = this.individualEntrepreneurToString(entity);
    if (entity.sro) {
      this.displayDeveloperSro = entity.sro.name + ", ОГРН " + entity.sro.ogrn + ", ИНН " + entity.sro.inn;
    } else {
      this.displayDeveloperSro = "н/у";
    }
    this.counterpartySelected(this.selectedCounterparty!)
  }

  legalEntitySelected(entity: LegalEntity) {
    this.dropCounterparty();
    this.selectedCounterparty!.organizationWithOptionalSro!.legalEntity = entity;
    this.displayDeveloperName = this.legalEntityToString(entity);
    if (entity.sro) {
      this.displayDeveloperSro = entity.sro.name + ", ОГРН " + entity.sro.ogrn + ", ИНН " + entity.sro.inn;
    } else {
      this.displayDeveloperSro = "н/у";
    }
    this.counterpartySelected(this.selectedCounterparty!)
  }

  /*
  Выбор застройщика из списка контрагентов
   */
  private ROLE: string = "DEVELOPER";
  counterpartyCreatedHidden: boolean = true;
  developerAsideHidden: boolean = true;

  showDeveloperList() {
    this.developerAsideHidden = false;
  }

  private getIndividualList() {
    this.service.getIndividualListByRole(this.ROLE).subscribe({
      next: value => {
        this.individualList = value;
      },
      error: err => console.log(err)
    });
  }

  private getIndividualEntrepreneurList() {
    this.service.getIndividualEntrepreneurListByRole(this.ROLE).subscribe({
      next: value => {
        this.individualEntrepreneurList = value;
      },
      error: err => console.log(err)
    });
  }

  private getLegalEntityList() {
    this.service.getLegalEntityListByRole(this.ROLE).subscribe({
      next: value => {
        this.legalEntityList = value;
      },
      error: err => console.log(err)
    });
  }

  /*
  Добавление нового контрагента
   */
  createdCounterPartyType: CounterpartyType | null = null;

  createNewCounterparty(counterpartyType: CounterpartyType) {
    this.createdCounterPartyType = counterpartyType;
    this.developerAsideHidden = true;
    this.counterpartyCreatedHidden = false;
  }

  onCounterpartyCreated(isCreated: boolean) {
    this.counterpartyCreatedHidden = true;
    this.developerAsideHidden = false;
    if (isCreated) {
      switch (this.createdCounterPartyType) {
        case CounterpartyType.INDIVIDUAL:
          this.getIndividualList();
          break;
        case CounterpartyType.INDIVIDUAL_ENTREPRENEUR:
          this.getIndividualEntrepreneurList();
          break;
        case CounterpartyType.LEGAL_ENTITY:
          this.getLegalEntityList();
          break;
      }
    }
  }

  /*
   Формирование строки для отображения контрагента
   */
  private individualToString(entity: Individual): string {
    return entity.fullNameGroup.lastName + " " +
      entity.fullNameGroup.firstName +
      (entity.fullNameGroup.middleName? (" " + entity.fullNameGroup.middleName) : "") + ", " +
      (entity.isaRussianFederationCitizen?
        ("паспорт серия: " + entity.passportDetails.passportDetailsRussianFederation.series +
          ", номер: " + entity.passportDetails.passportDetailsRussianFederation.number) :
        (entity.passportDetails.documentDetailsForeign.docName +
          " серия: " + entity.passportDetails.documentDetailsForeign.series +
          ", номер: " + entity.passportDetails.documentDetailsForeign.series)) + ", " +
      entity.address;
  }

  private individualEntrepreneurToString(entity: IndividualEntrepreneur): string {
    return entity.fullNameGroup.lastName + " " +
      entity.fullNameGroup.firstName +
      (entity.fullNameGroup.middleName? (" " + entity.fullNameGroup.middleName) : "") + ", " +
      entity.address + ", ОГРНИП " + entity.ogrnip + ", ИНН " + entity.inn;
  }

  private legalEntityToString(entity: LegalEntity): string {
    return entity.fullName + ", ОГРН " + entity.ogrn + ", ИНН " + entity.inn + ", "
      + entity.address + (entity.phone? (", т/ф: " + entity.phone) : "");
  }

}
