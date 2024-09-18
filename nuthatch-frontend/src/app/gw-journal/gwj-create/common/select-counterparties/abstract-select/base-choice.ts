import {Individual} from "../../../../../models/representative/Individual";
import {IndividualEntrepreneur} from "../../../../../models/representative/IndividualEntrepreneur";
import {LegalEntity} from "../../../../../models/representative/LegalEntity";
import {CounterpartiesService} from "../../counterparties.service";
import {
  IndividualEntrepreneurOrLegalEntityOrIndividualAndId
} from "../../../../models/IndividualEntrepreneurOrLegalEntityOrIndividualAndId";
import {Directive, EventEmitter, Output} from "@angular/core";
import {CounterpartyType} from "../../counterparty-type";
import {OrganizationWithOptionalSro} from "../../../../../models/representative/OrganizationWithOptionalSro";

@Directive()
export abstract class BaseChoice<T extends OrganizationWithOptionalSro |
  IndividualEntrepreneurOrLegalEntityOrIndividualAndId> {

  protected abstract role: string;

  protected individualList: Individual[] = [];
  protected individualEntrepreneurList: IndividualEntrepreneur[] = [];
  protected legalEntityList: LegalEntity[] = [];

  constructor(private service: CounterpartiesService) {
  }

  /*
  Сущность для формирования вывода, метод сброса полей
  */
  selectedCounterparty: T | null = null;

  abstract dropCounterparty(): void;

  /*
  Вывод выбранного представителя для формирования ОЖР
   */
  @Output() onCounterpartySelected =
    new EventEmitter<T>();

  @Output() counterpartyId = new EventEmitter<string>();

  counterpartySelected(counterparty: T,
                       counterpartyId: string) {
    this.onCounterpartySelected.emit(counterparty);
    this.counterpartyId.emit(counterpartyId);
    this.counterpartyAsideHidden = true;
  }

  /*
  Получение списков представителей в зависимости от роли контрагента
   */
  protected getIndividualList() {
    this.service.getIndividualListByRole(this.role).subscribe({
      next: value => {
        this.individualList = value;
      },
      error: err => console.log(err)
    });
  }

  protected getIndividualEntrepreneurList() {
    this.service.getIndividualEntrepreneurListByRole(this.role).subscribe({
      next: value => {
        this.individualEntrepreneurList = value;
      },
      error: err => console.log(err)
    });
  }

  protected getLegalEntityList() {
    this.service.getLegalEntityListByRole(this.role).subscribe({
      next: value => {
        this.legalEntityList = value;
      },
      error: err => console.log(err)
    });
  }

  // Отображение списка для выбора представителя
  counterpartyAsideHidden: boolean = true;

  showCounterpartyList() {
    this.counterpartyAsideHidden = false;
  }

  /*
  Отображение выбранного контрагента в качестве застройщика, отображение СРО (при наличии).
  Передача контрагента в виде IndividualEntrepreneurOrLegalEntityOrIndividualAndId
  для сохранения в ОЖР
   */
  displayCounterpartyName: string = " ";
  displayCounterpartySro: string = "н/у";

  abstract individualSelected(entity: Individual): void;

  abstract individualEntrepreneurSelected(entity: IndividualEntrepreneur): void;

  abstract legalEntitySelected(entity: LegalEntity): void;

  /*
   Формирование строки для отображения контрагента
   */
  protected individualToString(entity: Individual): string {
    return entity.fullNameGroup.lastName + " " +
      entity.fullNameGroup.firstName +
      (entity.fullNameGroup.middleName ? (" " + entity.fullNameGroup.middleName) : "") + ", " +
      (entity.isaRussianFederationCitizen ?
        ("паспорт серия: " + entity.passportDetails.passportDetailsRussianFederation.series +
          ", номер: " + entity.passportDetails.passportDetailsRussianFederation.number) :
        (entity.passportDetails.documentDetailsForeign.docName +
          " серия: " + entity.passportDetails.documentDetailsForeign.series +
          ", номер: " + entity.passportDetails.documentDetailsForeign.series)) + ", " +
      entity.address;
  }

  protected individualEntrepreneurToString(entity: IndividualEntrepreneur): string {
    return entity.fullNameGroup.lastName + " " +
      entity.fullNameGroup.firstName +
      (entity.fullNameGroup.middleName ? (" " + entity.fullNameGroup.middleName) : "") + ", " +
      entity.address + ", ОГРНИП " + entity.ogrnip + ", ИНН " + entity.inn;
  }

  protected legalEntityToString(entity: LegalEntity): string {
    return entity.fullName + ", ОГРН " + entity.ogrn + ", ИНН " + entity.inn + ", "
      + entity.address + (entity.phone ? (", т/ф: " + entity.phone) : "");
  }

  /*
  Добавление нового контрагента
   */
  counterpartyCreatedHidden: boolean = true;
  createdCounterPartyType: CounterpartyType | null = null;

  createNewCounterparty(counterpartyType: CounterpartyType) {
    this.createdCounterPartyType = counterpartyType;
    this.counterpartyAsideHidden = true;
    this.counterpartyCreatedHidden = false;
  }

  onCounterpartyCreated(isCreated: boolean) {
    this.counterpartyCreatedHidden = true;
    this.counterpartyAsideHidden = false;
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
