import {Component, EventEmitter, Input, Output, TemplateRef, ViewChild} from '@angular/core';
import {FormBuilder, FormGroup, ReactiveFormsModule, Validators} from "@angular/forms";
import {CounterpartiesService} from "../counterparties.service";
import {NgForOf, NgIf, NgTemplateOutlet} from "@angular/common";
import {CounterpartyType} from "../counterparty-type";
import {Individual} from "../../../../reference-book/organization-and-representative/models/Individual";

@Component({
  selector: 'app-create-counterparty',
  standalone: true,
  imports: [
    NgTemplateOutlet,
    NgIf,
    ReactiveFormsModule,
    NgForOf
  ],
  templateUrl: './create-counterparty.component.html',
  styleUrl: './create-counterparty.component.css'
})
export class CreateCounterpartyComponent {

  @ViewChild("individualTemplate", {static: false}) individualTemplate!: TemplateRef<any>;
  @ViewChild("individualEntrepreneurTemplate", {static: false}) individualEntrepreneurTemplate!: TemplateRef<any>;
  @ViewChild("legalEntityTemplate", {static: false}) legalEntityTemplate!: TemplateRef<any>;

  @Input() counterpartyType: CounterpartyType | null = null;

  @Output() onCounterpartyCreated = new EventEmitter<boolean>();

  counterpartyCreated(isCreated: boolean) {
    this.onCounterpartyCreated.emit(isCreated);
  }

  individualForm: FormGroup;
  individualEntrepreneurForm: FormGroup;
  legalEntityForm: FormGroup;

  constructor(private service: CounterpartiesService,
              private fb: FormBuilder) {
    // Форма для физического лица
    this.individualForm = this.fb.group({
      lastName: ["", Validators.required],
      firstName: ["", Validators.required],
      middleName: [""],
      address: ["", Validators.required],
      isaRussianFederationCitizen: [true],
      ru_series: [""],
      ru_number: [""],
      ru_dateIssue: [null],
      foreign_docName: [""],
      foreign_series: [""],
      foreign_number: [""],
      foreign_dateIssue: [null],
    });
    // Форма для индивидуального предпринимателя
    this.individualEntrepreneurForm = this.fb.group({
      lastName: ["", Validators.required],
      firstName: ["", Validators.required],
      middleName: [""],
      address: ["", Validators.required],
      ogrnip: ["", Validators.required],
      inn: ["", Validators.required],
      sro: [null],
    });
    // Форма для юридического лица
    this.legalEntityForm = this.fb.group({
      fullName: ["", Validators.required],
      shortName: ["", Validators.required],
      ogrn: ["", Validators.required],
      inn: ["", Validators.required],
      address: ["", Validators.required],
      phone: [""],
      sro: [null],
    });
  }

  loadTemplate(): TemplateRef<any> {
    switch (this.counterpartyType) {
      case CounterpartyType.INDIVIDUAL:
        return this.individualTemplate;
      case CounterpartyType.INDIVIDUAL_ENTREPRENEUR:
        return this.individualEntrepreneurTemplate;
    }
    return this.legalEntityTemplate;
  }

  // Controls для ФЛ
  get fi() {
    return this.individualForm.controls;
  }

  createIndividual() {
    let individual: Individual = {
      uuid: '',
      fullNameGroup: {
        lastName: this.fi['lastName'].value,
        firstName: this.fi['firstName'].value,
        middleName: this.fi['middleName'].value
      },
      address: this.fi['address'].value,
      isaRussianFederationCitizen: this.fi['isaRussianFederationCitizen'].value,
      passportDetails: {
        passportDetailsRussianFederation: {
          series: this.fi['ru_series'].value,
          number: this.fi['ru_number'].value,
          dateIssue: this.fi['ru_dateIssue'].value
        },
        documentDetailsForeign: {
          docName: this.fi['foreign_docName'].value,
          series: this.fi['foreign_series'].value,
          number: this.fi['foreign_number'].value,
          dateIssue: this.fi['foreign_dateIssue'].value
        }
      },
      roleSet: ["DEVELOPER"],
    }

    this.service.createIndividual(individual).subscribe({
      next: _ => {
        this.counterpartyCreated(true);
      },
      error: err => console.log(err)
    });
  }

}
