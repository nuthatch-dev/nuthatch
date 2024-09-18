import {Component, Directive, OnInit} from "@angular/core";
import {Individual} from "../../../../../models/representative/Individual";
import {IndividualEntrepreneur} from "../../../../../models/representative/IndividualEntrepreneur";
import {LegalEntity} from "../../../../../models/representative/LegalEntity";
import {
  IndividualEntrepreneurOrLegalEntityOrIndividualAndId
} from "../../../../models/IndividualEntrepreneurOrLegalEntityOrIndividualAndId";
import {BaseChoice} from "./base-choice";

@Component({
  template: ``,
})
export abstract class ChoiceOfAllTypes
  extends BaseChoice<IndividualEntrepreneurOrLegalEntityOrIndividualAndId> implements OnInit {

  ngOnInit() {
    this.getIndividualList();
    this.getIndividualEntrepreneurList();
    this.getLegalEntityList();
  }

  /*
  Сущность для формирования вывода, метод сброса полей
  */
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
  Отображение выбранного контрагента в качестве застройщика, отображение СРО (при наличии).
  Передача контрагента в виде IndividualEntrepreneurOrLegalEntityOrIndividualAndId
  для сохранения в ОЖР
   */
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

}
