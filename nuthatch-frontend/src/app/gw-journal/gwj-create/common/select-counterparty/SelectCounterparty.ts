import {Individual} from "../../../../models/representative/Individual";
import {IndividualEntrepreneur} from "../../../../models/representative/IndividualEntrepreneur";
import {LegalEntity} from "../../../../models/representative/LegalEntity";
import {Directive, EventEmitter, OnInit, Output} from "@angular/core";
import {CounterpartiesService} from "../counterparties.service";
import {
  IndividualEntrepreneurOrLegalEntityOrIndividualAndId
} from "../../../models/IndividualEntrepreneurOrLegalEntityOrIndividualAndId";
import {CounterpartyType} from "../counterparty-type";

@Directive()
export abstract class SelectCounterparty implements OnInit {

  protected abstract role: string;

  individualList: Individual[] = [];
  individualEntrepreneurList: IndividualEntrepreneur[] = [];
  legalEntityList: LegalEntity[] = [];


  protected constructor(private service: CounterpartiesService) {
  }

  ngOnInit() {
    this.getIndividualList();
    this.getIndividualEntrepreneurList();
    this.getLegalEntityList();
  }

  /*
  Сущность для формирования вывода, метод сброса полей
  */
  selectedCounterparty: IndividualEntrepreneurOrLegalEntityOrIndividualAndId | null = null;

  dropCounterparty() {
    this.selectedCounterparty = {
      uuid: "",
      organizationWithOptionalSro: {
        uuid: "",
        legalEntity: null,
        individualEntrepreneur: null,
      },
      individual: null,
    };
  }

  /*
  Вывод выбранного представителя для формирования ОЖР
   */
  @Output() onCounterpartySelected =
    new EventEmitter<IndividualEntrepreneurOrLegalEntityOrIndividualAndId>();

  @Output() counterpartyId = new EventEmitter<string>();

  counterpartySelected(counterparty: IndividualEntrepreneurOrLegalEntityOrIndividualAndId,
                       counterpartyId: string) {
    this.onCounterpartySelected.emit(counterparty);
    this.counterpartyId.emit(counterpartyId);
    this.counterpartyAsideHidden = true;
  }

  /*
  Получение списков представителей в зависимости от роли контрагента
   */
  private getIndividualList() {
    this.service.getIndividualListByRole(this.role).subscribe({
      next: value => {
        this.individualList = value;
      },
      error: err => console.log(err)
    });
  }

  private getIndividualEntrepreneurList() {
    this.service.getIndividualEntrepreneurListByRole(this.role).subscribe({
      next: value => {
        this.individualEntrepreneurList = value;
      },
      error: err => console.log(err)
    });
  }

  private getLegalEntityList() {
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

  individualSelected(entity: Individual) {
    this.dropCounterparty();
    this.selectedCounterparty!.individual = entity;
    this.displayCounterpartyName = this.individualToString(entity);
    this.displayCounterpartySro = "н/у";
    this.counterpartySelected(this.selectedCounterparty!, this.selectedCounterparty!.individual.uuid)
  }

  individualEntrepreneurSelected(entity: IndividualEntrepreneur) {
    this.dropCounterparty();
    this.selectedCounterparty!.organizationWithOptionalSro!.individualEntrepreneur = entity;
    this.displayCounterpartyName = this.individualEntrepreneurToString(entity);
    if (entity.sro) {
      this.displayCounterpartySro = entity.sro.name + ", ОГРН " + entity.sro.ogrn + ", ИНН " + entity.sro.inn;
    } else {
      this.displayCounterpartySro = "н/у";
    }
    this.counterpartySelected(this.selectedCounterparty!,
      this.selectedCounterparty!.organizationWithOptionalSro!.individualEntrepreneur.uuid)
  }

  legalEntitySelected(entity: LegalEntity) {
    this.dropCounterparty();
    this.selectedCounterparty!.organizationWithOptionalSro!.legalEntity = entity;
    this.displayCounterpartyName = this.legalEntityToString(entity);
    if (entity.sro) {
      this.displayCounterpartySro = entity.sro.name + ", ОГРН " + entity.sro.ogrn + ", ИНН " + entity.sro.inn;
    } else {
      this.displayCounterpartySro = "н/у";
    }
    this.counterpartySelected(this.selectedCounterparty!,
      this.selectedCounterparty!.organizationWithOptionalSro!.legalEntity.uuid)
  }

  /*
   Формирование строки для отображения контрагента
   */
  private individualToString(entity: Individual): string {
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

  private individualEntrepreneurToString(entity: IndividualEntrepreneur): string {
    return entity.fullNameGroup.lastName + " " +
      entity.fullNameGroup.firstName +
      (entity.fullNameGroup.middleName ? (" " + entity.fullNameGroup.middleName) : "") + ", " +
      entity.address + ", ОГРНИП " + entity.ogrnip + ", ИНН " + entity.inn;
  }

  private legalEntityToString(entity: LegalEntity): string {
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
