import {ChoiceOfOrganizationTypes} from "./common/choice-of-organization-types";
import {CounterpartyType} from "../counterparty-type";
import {Component} from "@angular/core";
import {CreateCounterpartyComponent} from "../create-counterparty/create-counterparty.component";

@Component({
  selector: "app-regional-operator-choice",
  standalone: true,
  imports: [
    CreateCounterpartyComponent
  ],
  templateUrl: "./template/regional-operator-template.html"
})
export class RegionalOperatorChoice extends ChoiceOfOrganizationTypes {

  protected role: string = "REGIONAL_OPERATOR";
  protected readonly CounterpartyType = CounterpartyType;

}
