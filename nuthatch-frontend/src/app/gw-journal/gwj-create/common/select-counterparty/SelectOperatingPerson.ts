import {SelectCounterparty} from "./SelectCounterparty";
import {CounterpartiesService} from "../counterparties.service";
import {Component} from "@angular/core";
import {CommonSelectTemplateComponent} from "../common-template/common-select-template.component";

@Component({
  selector: 'app-select-operating-person',
  standalone: true,
  imports: [
    CommonSelectTemplateComponent
  ],
  template: `
    <app-common-select-template
      [role]="role"
      [individualList]="individualList"
      [individualEntrepreneurList]="individualEntrepreneurList"
      [legalEntityList]="legalEntityList"
      [displayCounterpartyName]="displayCounterpartyName"
      [displayCounterpartySro]="displayCounterpartySro"
      [counterpartyAsideHidden]="counterpartyAsideHidden"
      [counterpartyCreatedHidden]="counterpartyCreatedHidden"
      [createdCounterpartyType]="createdCounterPartyType"
      (showCounterpartyListEvent)="showCounterpartyList()"
      (individualSelectedEvent)="individualSelected($event)"
      (individualEntrepreneurSelectedEvent)="individualEntrepreneurSelected($event)"
      (legalEntitySelectedEvent)="legalEntitySelected($event)"
      (createNewCounterpartyEvent)="createNewCounterparty($event)"
      (onCounterpartyCreatedEvent)="onCounterpartyCreated($event)">
    </app-common-select-template>
  `
})

export class SelectOperatingPerson extends SelectCounterparty {

  protected role: string = "OPERATING_PERSON";

  constructor(service: CounterpartiesService) {
    super(service);
  }
}
