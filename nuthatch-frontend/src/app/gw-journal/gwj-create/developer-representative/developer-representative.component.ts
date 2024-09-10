import {Component, EventEmitter, Input, OnChanges, Output, SimpleChanges, TemplateRef, ViewChild} from '@angular/core';
import {DeveloperRepresentativeService} from "./developer-representative.service";
import {Representative} from "../../../models/representative/Representative";
import {NgIf, NgTemplateOutlet} from "@angular/common";
import {FormBuilder, FormGroup, ReactiveFormsModule, Validators} from "@angular/forms";

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

  @Input() developerId: string | undefined;

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
    this.service.getRepresentativeListByCounterpartyId(changes["developerId"].currentValue).subscribe({
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
}
