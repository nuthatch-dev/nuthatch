import {Component, OnInit} from '@angular/core';
import {IndividualService} from "../individual.service";
import {Individual} from "../../models/Individual";
import {DatePipe, NgForOf, NgIf} from "@angular/common";
import {FormBuilder, FormGroup, ReactiveFormsModule, Validators} from "@angular/forms";
import {RoleMap} from "../../common/RoleMap";
import {dateFormat} from "../../../common/CommonMethod";
import {RoleManagement} from "../../common/RoleManagement";

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
export class IndividualListComponent extends RoleManagement<Individual> implements OnInit {

  individualList: Individual[] = [];
  formGroup: FormGroup;

  constructor(private service: IndividualService,
              private fb: FormBuilder) {
    super();
    this.formGroup = this.fb.group({
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

  override entity: Individual = {
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

  individualDetails(individual: Individual) {
    this.entity = individual;
  }

  entityIsCreated: boolean = true;

  /*
  Настройка данных модального окна при редактировании записи
   */
  onUpdateClick() {
    this.entityIsCreated = false;
    this.prepareRoleMaps();

    this.formGroup.patchValue({
      lastName: this.entity.fullNameGroup.lastName,
      firstName: this.entity.fullNameGroup.firstName,
      middleName: this.entity.fullNameGroup.middleName,
      address: this.entity.address,
      roleSet: this.entity.roleSet,
    });
    if (this.entity.isaRussianFederationCitizen) {
      this.formGroup.patchValue({
        ru_series: this.entity.passportDetails.passportDetailsRussianFederation.series,
        ru_number: this.entity.passportDetails.passportDetailsRussianFederation.number,
        ru_dateIssue: dateFormat(
          this.entity.passportDetails.passportDetailsRussianFederation.dateIssue
        ),
      });
    } else {
      this.formGroup.patchValue({
        foreign_docName: this.entity.passportDetails.documentDetailsForeign.docName,
        foreign_series: this.entity.passportDetails.documentDetailsForeign.series,
        foreign_number: this.entity.passportDetails.documentDetailsForeign.number,
        foreign_dateIssue: dateFormat(
          this.entity.passportDetails.documentDetailsForeign.dateIssue
        ),
      });
    }
  }

  /*
  Настройка данных модального окна при создании новой записи
   */
  onCreateClick() {
    this.entityIsCreated = true;
    this.formGroup.reset();
    this.formGroup.patchValue({
      isaRussianFederationCitizen: true,
    });
    this.assignedRoleMap = new Map<string, string>();
    this.freeRoleMap = new Map<string, string>();
    RoleMap.forEach((value, key) => this.freeRoleMap.set(key, value));
  }

  get f() {
    return this.formGroup.controls;
  }

  saveIndividual() {
    let individual: Individual = {
      uuid: this.entityIsCreated ? '' : this.entity.uuid,
      fullNameGroup: {
        lastName: this.f['lastName'].value,
        firstName: this.f['firstName'].value,
        middleName: this.f['middleName'].value
      },
      address: this.f['address'].value,
      isaRussianFederationCitizen: this.entityIsCreated ?
        this.f['isaRussianFederationCitizen'].value :
        this.entity.isaRussianFederationCitizen,
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
          dateIssue: this.f['foreign_dateIssue'].value
        }
      },
      roleSet: Array.from(this.assignedRoleMap.keys()),
    }
    if (this.entityIsCreated) {
      this.service.createIndividual(individual).subscribe({
        next: value => {
          this.individualList.unshift(value);
        },
        error: err => console.log(err)
      });
    } else {
      this.service.updateIndividual(individual).subscribe({
        next: _ => {
          this.getAllIndividuals();
        },
        error: err => console.log(err)
      });
    }
  }

  deleteIndividual() {
    this.service.deleteIndividualById(this.entity.uuid).subscribe({
      next: _ => {
        this.getAllIndividuals();
      },
      error: err => console.log(err)
    });
  }
}
