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
  templateUrl: './select-developer.component.html',
  styleUrl: './select-developer.component.css'
})
export class SelectDeveloperComponent implements OnInit {

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

  selectedCounterparty: IndividualEntrepreneurOrLegalEntityOrIndividualAndId = {
    uuid: "",
    organizationWithOptionalSro: {
      legalEntity: null,
      individualEntrepreneur: null,
    },
    individual: null,
  };

  // TODO: Паспортные данные
  individualSelected(entity: Individual) {
    this.selectedCounterparty.individual = entity;
    this.displayDeveloperName = entity.fullNameGroup.lastName + " " +
      entity.fullNameGroup.firstName + " " + entity.fullNameGroup.middleName + ", " +
      entity.address;
    this.displayDeveloperSro = "н/у";
    this.counterpartySelected(this.selectedCounterparty!)
  }

  individualEntrepreneurSelected(entity: IndividualEntrepreneur) {
    this.selectedCounterparty.organizationWithOptionalSro!.individualEntrepreneur = entity;
    this.displayDeveloperName = entity.fullNameGroup.lastName + " " +
      entity.fullNameGroup.firstName + " " + entity.fullNameGroup.middleName + ", " +
      entity.address + ", ОГРНИП " + entity.ogrnip + ", ИНН " + entity.inn;
    if (entity.sro) {
      this.displayDeveloperSro = entity.sro.name + ", ОГРН " + entity.sro.ogrn + ", ИНН " + entity.sro.inn;
    } else {
      this.displayDeveloperSro = "н/у";
    }
    this.counterpartySelected(this.selectedCounterparty!)
  }

  legalEntitySelected(entity: LegalEntity) {
    this.selectedCounterparty.organizationWithOptionalSro!.legalEntity = entity;
    this.displayDeveloperName = entity.fullName + ", ОГРН " + entity.ogrn + ", ИНН " + entity.inn + ", "
      + entity.address + ", т. " + entity.phone;
    if (entity.sro) {
      this.displayDeveloperSro = entity.sro.name + ", ОГРН " + entity.sro.ogrn + ", ИНН " + entity.sro.inn;
    } else {
      this.displayDeveloperSro = "н/у";
    }
    this.counterpartySelected(this.selectedCounterparty!)
  }

  displayDeveloperName: string = " ";
  displayDeveloperSro: string = "н/у";

  private ROLE: string = "DEVELOPER";

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

  counterpartyCreatedHidden: boolean = true;
  developerAsideHidden: boolean = true;

  showDeveloperList() {
    this.developerAsideHidden = !this.developerAsideHidden;
  }

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

}
