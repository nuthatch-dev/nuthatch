import {CommonSelect} from "./common/common-select";
import {Component} from "@angular/core";
import {NgIf, NgTemplateOutlet} from "@angular/common";
import {ReactiveFormsModule} from "@angular/forms";

@Component({
  selector: "app-developer-representative-select",
  standalone: true,
  imports: [
    NgIf,
    ReactiveFormsModule,
    NgTemplateOutlet
  ],
  templateUrl: "./template/developer-representative-template.html"
})
export class DeveloperRepresentativeSelect extends CommonSelect {
}
