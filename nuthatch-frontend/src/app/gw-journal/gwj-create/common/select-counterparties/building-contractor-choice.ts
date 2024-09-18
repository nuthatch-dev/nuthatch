import {ChoiceOfOrganizationTypes} from "./abstract-select/choice-of-organization-types";
import {CounterpartyType} from "../counterparty-type";
import {Component} from "@angular/core";
import {CreateCounterpartyComponent} from "../create-counterparty/create-counterparty.component";

@Component({
  selector: "app-building-contractor-choice",
  standalone: true,
  imports: [
    CreateCounterpartyComponent
  ],
  templateUrl: "./template/building-contractor-template.html"
})
export class BuildingContractorChoice extends ChoiceOfOrganizationTypes {

  protected role: string = "BUILDING_CONTRACTOR";
  protected readonly CounterpartyType = CounterpartyType;

}
