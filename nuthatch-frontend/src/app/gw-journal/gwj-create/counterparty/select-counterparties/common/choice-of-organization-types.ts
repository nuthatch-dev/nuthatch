import {Directive, OnInit} from "@angular/core";
import {IndividualEntrepreneur} from "../../../../../models/representative/IndividualEntrepreneur";
import {LegalEntity} from "../../../../../models/representative/LegalEntity";
import {OrganizationWithOptionalSro} from "../../../../../models/representative/OrganizationWithOptionalSro";
import {BaseChoice} from "./base-choice";
import {Individual} from "../../../../../models/representative/Individual";

@Directive()
export abstract class ChoiceOfOrganizationTypes extends BaseChoice<OrganizationWithOptionalSro> implements OnInit {

  ngOnInit() {
    this.getIndividualEntrepreneurList();
    this.getLegalEntityList();
  }

  /*
  Сущность для формирования вывода, метод сброса полей
  */
  dropCounterparty() {
    this.selectedCounterparty = {
      uuid: "",
      legalEntity: null,
      individualEntrepreneur: null,
    };
  }

  /*
  Отображение выбранного контрагента, отображение СРО (при наличии).
  Передача контрагента в виде IndividualEntrepreneurOrLegalEntityOrIndividualAndId
  для сохранения в ОЖР
   */
  individualSelected(entity: Individual): void {};

  individualEntrepreneurSelected(entity: IndividualEntrepreneur) {
    this.dropCounterparty();
    this.selectedCounterparty!.individualEntrepreneur = entity;
    this.displayCounterpartyName = this.individualEntrepreneurToString(entity);
    if (entity.sro) {
      this.displayCounterpartySro = entity.sro.name + ", ОГРН " + entity.sro.ogrn + ", ИНН " + entity.sro.inn;
    } else {
      this.displayCounterpartySro = "н/у";
    }
    this.counterpartySelected(this.selectedCounterparty!,
      this.selectedCounterparty!.individualEntrepreneur.uuid)
  }

  legalEntitySelected(entity: LegalEntity) {
    this.dropCounterparty();
    this.selectedCounterparty!.legalEntity = entity;
    this.displayCounterpartyName = this.legalEntityToString(entity);
    if (entity.sro) {
      this.displayCounterpartySro = entity.sro.name + ", ОГРН " + entity.sro.ogrn + ", ИНН " + entity.sro.inn;
    } else {
      this.displayCounterpartySro = "н/у";
    }
    this.counterpartySelected(this.selectedCounterparty!,
      this.selectedCounterparty!.legalEntity.uuid)
  }

}
