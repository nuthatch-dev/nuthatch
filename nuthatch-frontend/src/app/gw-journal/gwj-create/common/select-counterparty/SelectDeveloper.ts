import {Component} from "@angular/core";
import {SelectCounterparty} from "./SelectCounterparty";
import {CommonSelectTemplateComponent} from "../common-template/common-select-template.component";
import {CounterpartiesService} from "../counterparties.service";

@Component({
  selector: 'app-select-developer',
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

export class SelectDeveloper extends SelectCounterparty {

  protected role: string = "DEVELOPER";

  constructor(service: CounterpartiesService) {
    super(service);
  }

}
