import {Component, OnInit} from '@angular/core';
import {IndividualService} from "../individual.service";
import {Router} from "@angular/router";
import {Individual} from "../../models/Individual";
import {DatePipe, NgForOf, NgIf} from "@angular/common";
import {FormBuilder, FormGroup, ReactiveFormsModule, Validators} from "@angular/forms";
import {min} from "rxjs";
import {Role} from "../../models/Role";

@Component({
  selector: 'app-individual-list',
  standalone: true,
  imports: [
    NgForOf,
    NgIf,
    DatePipe,
    ReactiveFormsModule
  ],
  templateUrl: './individual-list.component.html',
  styleUrl: './individual-list.component.css'
})
export class IndividualListComponent implements OnInit {

  individualList: Individual[] = [];
  updateForm: FormGroup;

  constructor(private service: IndividualService,
              private router: Router,
              private fb: FormBuilder) {

    this.updateForm = this.fb.group({
      lastName: ["", Validators.required],
      firstName: ["", Validators.required],
      middleName: [""],
      address: ["", Validators.required],
      ru_series: [""],
      ru_number: [""],
      ru_dateIssue: [""],
      foreign_docName: [""],
      foreign_series: [""],
      foreign_number: [""],
      foreign_datIssue: [""],
      roleSet: [[]],
    });

  }

  ngOnInit(): void {
    this.getAllIndividuals();
  }

  private getAllIndividuals() {
    this.service.getAllIndividuals().subscribe({
      next: value => {
        this.individualList = value;
      },
      error: err => console.log(err)
    });
  }

  individualDetails(id: string) {
    this.service.getIndividualById(id).subscribe({
      next: value => {
        this.individual = value;
      },
      error: err => console.log(err)
    });
  }

  individual: Individual = {
    uuid: "",
    fullNameGroup: {
      lastName: "",
      firstName: "",
      middleName: ""
    },
    address: '',
    isaRussianFederationCitizen: true,
    passportDetails: {
      passportDetailsRussianFederation: {
        series: "",
        number: "",
        dateIssue: new Date(),
      },
      documentDetailsForeign: {
        docName: "",
        series: "",
        number: "",
        dateIssue: new Date(),
      }
    },
    roleSet: []
  }

  submitted: boolean = false;
  roleList: Role[] = [];

  onUpdateClick() {
    this.service.getRoleList().subscribe({
      next: value => {
        this.roleList = value;
      },
      error: err => console.log(err)
    });

    this.updateForm.patchValue({
      lastName: this.individual.fullNameGroup.lastName,
      firstName: this.individual.fullNameGroup.firstName,
      middleName: this.individual.fullNameGroup.middleName,
      address: this.individual.address,
      roleSet: this.individual.roleSet,
    });
    if (this.individual.isaRussianFederationCitizen) {
      this.updateForm.patchValue({
        ru_series: this.individual.passportDetails.passportDetailsRussianFederation.series,
        ru_number: this.individual.passportDetails.passportDetailsRussianFederation.number,
        ru_dateIssue: this.individual.passportDetails.passportDetailsRussianFederation.dateIssue,
      });
    } else {
        this.updateForm.patchValue({
          foreign_docName: this.individual.passportDetails.documentDetailsForeign.docName,
          foreign_series: this.individual.passportDetails.documentDetailsForeign.series,
          foreign_number: this.individual.passportDetails.documentDetailsForeign.number,
          foreign_datIssue: this.individual.passportDetails.documentDetailsForeign.dateIssue,
        });
    }
  }

  get f() {
    return this.updateForm.controls;
  }

  updateIndividual() {
    this.submitted = true;
    if (this.updateForm.invalid) {
      return;
    }
    let individual: Individual = {
      uuid: this.individual.uuid,
      fullNameGroup: {
        lastName: this.f['lastName'].value,
        firstName: this.f['firstName'].value,
        middleName: this.f['middleName'].value
      },
      address: this.f['address'].value,
      isaRussianFederationCitizen: this.individual.isaRussianFederationCitizen,
      passportDetails: {
        passportDetailsRussianFederation: {
          series: this.f['ru_series'].value,
          number: this.f['ru_number'].value,
          dateIssue: this.f['ru_dateIssue'].value
        },
        documentDetailsForeign: {
          docName: this.f['foreign_docName'].value,
          series: this.f['foreign_series'].value,
          number: this.f['foreign_number'].value,
          dateIssue: this.f['foreign_datIssue'].value
        }
      },
      roleSet: this.f['roleSet'].value
    }
    this.service.createIndividual(individual).subscribe({
      next: value => {
        this.getAllIndividuals();
        console.log(value);
      },
      error: err => console.log(err)
    });
  }

  createIndividual() {
  }

}
