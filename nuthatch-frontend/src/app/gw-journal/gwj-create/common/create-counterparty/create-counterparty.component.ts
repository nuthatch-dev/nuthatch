import {Component, EventEmitter, Input, OnInit, Output, TemplateRef, ViewChild} from '@angular/core';
import {FormBuilder, FormGroup, ReactiveFormsModule, Validators} from "@angular/forms";
import {CounterpartiesService} from "../counterparties.service";
import {NgForOf, NgIf, NgTemplateOutlet} from "@angular/common";
import {CounterpartyType} from "../counterparty-type";
import {Individual} from "../../../../reference-book/organization-and-representative/models/Individual";
import {Sro} from "../../../../reference-book/organization-and-representative/models/Sro";
import {
  IndividualEntrepreneur
} from "../../../../reference-book/organization-and-representative/models/IndividualEntrepreneur";
import {LegalEntity} from "../../../../reference-book/organization-and-representative/models/LegalEntity";

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
export class CreateCounterpartyComponent implements OnInit {

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
  private ROLE: string = "DEVELOPER";

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

  ngOnInit() {
    this.getSroList();
  }

  loadTemplate() {
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
      roleSet: [this.ROLE],
    }

    this.service.createIndividual(individual).subscribe({
      next: _ => {
        this.counterpartyCreated(true);
      },
      error: err => console.log(err)
    });
  }

  // Методы для СРО
  isSroMember: boolean = false;
  sroList: Sro[] = [];

  setSroSwitch(value: boolean) {
    this.isSroMember = value;
  }

  private getSroList() {
    this.service.getSroList().subscribe({
      next: value => {
        this.sroList = value;
      },
      error: err => console.log(err)
    });
  }

  // Controls для ИП
  get fie() {
    return this.individualEntrepreneurForm.controls;
  }

  createIndividualEntrepreneur() {
    let ie: IndividualEntrepreneur = {
      uuid: "",
      fullNameGroup: {
        lastName: this.fie["lastName"].value,
        firstName: this.fie["firstName"].value,
        middleName: this.fie["middleName"].value
      },
      address: this.fie["address"].value,
      ogrnip: this.fie["ogrnip"].value,
      inn: this.fie["inn"].value,
      sro: this.fie["sro"].value,
      roleSet: [this.ROLE],
    };
    this.service.createIndividualEntrepreneur(ie).subscribe({
      next: _ => {
        this.counterpartyCreated(true);
      },
      error: err => console.log(err)
    });
  }

  // Controls для ЮЛ
  get fle() {
    return this.legalEntityForm.controls;
  }

  createLegalEntity() {
    let legalEntity: LegalEntity = {
      uuid: "",
      fullName: this.fle["fullName"].value,
      shortName: this.fle["shortName"].value,
      ogrn: this.fle["ogrn"].value,
      inn: this.fle["inn"].value,
      address: this.fle["address"].value,
      phone: this.fle["phone"].value,
      sro: this.fle["sro"].value,
      roleSet: [this.ROLE],
    }
    this.service.createLegalEntity(legalEntity).subscribe({
      next: _ => {
        this.counterpartyCreated(true);
      },
      error: err => console.log(err)
    });
  }

}
