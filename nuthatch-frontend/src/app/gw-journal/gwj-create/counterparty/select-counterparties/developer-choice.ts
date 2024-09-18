import {ChoiceOfAllTypes} from "./common/choice-of-all-types";
import {Component} from "@angular/core";
import {CreateCounterpartyComponent} from "../create-counterparty/create-counterparty.component";
import {CounterpartyType} from "../counterparty-type";

@Component({
  selector: "app-developer-choice",
  standalone: true,
  templateUrl: "./template/developer-template.html",
  imports: [
    CreateCounterpartyComponent
  ]
})
export class DeveloperChoice extends ChoiceOfAllTypes {

  protected role: string = "DEVELOPER";
  protected readonly CounterpartyType = CounterpartyType;

}
