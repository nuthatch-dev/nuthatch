import {ChoiceOfAllTypes} from "./common/choice-of-all-types";
import {CounterpartyType} from "../counterparty-type";
import {Component} from "@angular/core";
import {CreateCounterpartyComponent} from "../create-counterparty/create-counterparty.component";

@Component({
  selector: "app-operation-person-choice",
  standalone: true,
  imports: [
    CreateCounterpartyComponent
  ],
  templateUrl: "./template/operation-person-template.html"
})
export class OperationPersonChoice extends ChoiceOfAllTypes {

  protected role: string = "OPERATING_PERSON";
  protected readonly CounterpartyType = CounterpartyType;

}
