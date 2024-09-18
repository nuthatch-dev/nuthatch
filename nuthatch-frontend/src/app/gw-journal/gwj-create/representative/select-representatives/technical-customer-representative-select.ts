import {CommonSelect} from "./common/common-select";
import {Component} from "@angular/core";
import {NgIf, NgTemplateOutlet} from "@angular/common";
import {ReactiveFormsModule} from "@angular/forms";

@Component({
  selector: "app-technical-customer-representative-select",
  standalone: true,
  imports: [
    NgIf,
    ReactiveFormsModule,
    NgTemplateOutlet
  ],
  templateUrl: "./template/technical-customer-representative-template.html"
})
export class TechnicalCustomerRepresentativeSelect extends CommonSelect {
}
