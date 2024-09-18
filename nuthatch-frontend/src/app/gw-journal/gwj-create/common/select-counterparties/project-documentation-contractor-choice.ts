import {ChoiceOfOrganizationTypes} from "./abstract-select/choice-of-organization-types";
import {CounterpartyType} from "../counterparty-type";
import {Component} from "@angular/core";
import {CreateCounterpartyComponent} from "../create-counterparty/create-counterparty.component";

@Component({
  selector: "app-project-documentation-contractor-choice",
  standalone: true,
  imports: [
    CreateCounterpartyComponent
  ],
  templateUrl: "./template/project-documentation-contractor-template.html"
})
export class ProjectDocumentationContractorChoice extends ChoiceOfOrganizationTypes {

  protected role: string = "DESIGNER_CONTRACTOR";
  protected readonly CounterpartyType = CounterpartyType;

}
