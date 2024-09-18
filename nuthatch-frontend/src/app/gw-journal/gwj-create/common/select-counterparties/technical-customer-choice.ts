import {ChoiceOfOrganizationTypes} from "./abstract-select/choice-of-organization-types";
import {CounterpartyType} from "../counterparty-type";
import {Component} from "@angular/core";
import {CreateCounterpartyComponent} from "../create-counterparty/create-counterparty.component";

@Component({
  selector: "app-technical-customer-choice",
  standalone: true,
  imports: [
    CreateCounterpartyComponent
  ],
  templateUrl: "./template/technical-customer-template.html"
})
export class TechnicalCustomerChoice extends ChoiceOfOrganizationTypes {

  protected role: string = "TECHNICAL_CUSTOMER";
  protected readonly CounterpartyType = CounterpartyType;

}
