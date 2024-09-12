import {Component, EventEmitter, Input, OnChanges, Output, SimpleChanges, TemplateRef, ViewChild} from '@angular/core';
import {DeveloperRepresentativeService} from "./developer-representative.service";
import {Representative} from "../../../models/representative/Representative";
import {NgIf, NgTemplateOutlet} from "@angular/common";
import {FormBuilder, FormGroup, ReactiveFormsModule, Validators} from "@angular/forms";
import {CustomDocument} from "../../../models/administrative-document/CustomDocument";
import {OrganizationWithOptionalSro} from "../../../models/representative/OrganizationWithOptionalSro";

@Component({
  selector: 'app-developer-representative',
  standalone: true,
  imports: [
    NgTemplateOutlet,
    ReactiveFormsModule,
    NgIf
  ],
  templateUrl: './developer-representative.component.html',
  styleUrl: './developer-representative.component.css'
})
export class DeveloperRepresentativeComponent implements OnChanges {

  availableRepresentativeList: Representative[] = [];
  selectedRepresentativeList: Representative[] = [];

  @ViewChild("selectFromListTemplate", {static: false}) selectFromListTemplate!: TemplateRef<any>;
  @ViewChild("createRepresentativeTemplate", {static: false}) createRepresentativeTemplate!: TemplateRef<any>;
  selectedTemplate: TemplateRef<any> | null = null;

  @Input() developer: OrganizationWithOptionalSro | null = null;
  @Input() developerId: string = "";

  @Output() onRepresentativeSelected = new EventEmitter<string[]>();

  private representativeSelected() {
    this.onRepresentativeSelected.emit(this.selectedRepresentativeList.map(r => r.uuid));
  }

  createRepresentativeFormGroup: FormGroup;

  constructor(private service: DeveloperRepresentativeService,
              private fb: FormBuilder) {
    this.createRepresentativeFormGroup = this.fb.group({
      lastName: ["", Validators.required],
      firstName: ["", Validators.required],
      middleName: [""],
      position: ["", Validators.required],
      nostroyNumber: [""],
      documentName: ["", Validators.required],
      documentDate: [null, Validators.required],
      documentNumber: ["", Validators.required],
    });
  }

  ngOnChanges(changes: SimpleChanges): void {
    let developerId: string = changes["developerId"].currentValue;
    this.service.getRepresentativeListByCounterpartyId(developerId).subscribe({
      next: value => {
        if (value.length == 0) {
          this.selectedTemplate = this.createRepresentativeTemplate;
        } else {
          this.availableRepresentativeList = value;
          this.selectedTemplate = this.selectFromListTemplate;
        }
      },
      error: err => console.log(err)
    });
  }

  addToList(r: Representative) {
    this.availableRepresentativeList.splice(this.availableRepresentativeList.indexOf(r), 1);
    this.selectedRepresentativeList.push(r);
    this.representativeSelected();
  }

  removeFromList(r: Representative) {
    this.selectedRepresentativeList.splice(this.selectedRepresentativeList.indexOf(r), 1);
    this.availableRepresentativeList.push(r);
    this.representativeSelected();
  }

  onCreateRepresentativeClick() {
    this.selectedTemplate = this.createRepresentativeTemplate;
  }

  get f() {
    return this.createRepresentativeFormGroup.controls;
  }

  createRepresentative() {
    let document: CustomDocument = {
      uuid: "",
      docInfoGroup: {
        name: this.f["documentName"].value,
        number: this.f["documentNumber"].value,
      },
      date: this.f["documentDate"].value,
      beginningDate: null,
      expirationDate: null,
      attachedFile: null,
      tagSet: [],
    }
    this.service.createCustomDocument(document).subscribe({
      next: cd => {
        let representative: Representative = {
          uuid: "",
          fullNameGroup: {
            lastName: this.f["lastName"].value,
            firstName: this.f["firstName"].value,
            middleName: this.f["middleName"].value,
          },
          organization: this.developer!,
          position: this.f["position"].value,
          nostroyNumber: this.f["nostroyNumber"].value,
          administrativeDocument: cd.uuid,
        }
        this.service.createRepresentative(representative).subscribe({
          next: r => {
            this.addToList(r);
          },
          error: err => console.log(err)
        });
      },
      error: err => console.log(err)
    });


  }
}
