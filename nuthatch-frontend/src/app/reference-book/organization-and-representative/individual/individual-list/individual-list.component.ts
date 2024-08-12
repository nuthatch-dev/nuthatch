import {Component, OnInit} from '@angular/core';
import {IndividualService} from "../individual.service";
import {Individual} from "../../models/Individual";
import {DatePipe, NgForOf, NgIf} from "@angular/common";
import {FormBuilder, FormGroup, ReactiveFormsModule, Validators} from "@angular/forms";
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
  formGroup: FormGroup;

  constructor(private service: IndividualService,
              private fb: FormBuilder) {

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
    this.getRoleList();
  }

  private getAllIndividuals() {
    this.service.getAllIndividuals().subscribe({
      next: value => {
        this.individualList = value;
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

  individualDetails(individual: Individual) {
    this.individual = individual;
  }

  /*
  Методы для работы с ролями
   */
  roleList: Role[] = [];

  private getRoleList() {
    this.service.getRoleList().subscribe({
      next: value => {
        this.roleList = value;
      },
      error: err => console.log(err)
    });
  }

  /*
  Массивы ролей определенных для сущности и доступных к добавлению.
  Подготовка массива доступных ролей
   */
  freeRoleList: Role[] = [];
  assignedRoleList: Role[] = [];

  private prepareFreeRoleList() {
    this.assignedRoleList = this.individual.roleSet;
    this.freeRoleList = this.roleList.filter(role => {
      for (let r of this.individual.roleSet) {
        if (r.uuid == role.uuid) {
          return false;
        }
      }
      return true;
    });
  }

  /*
  Изменение массивов ролей по действиям пользователя
   */
  onAddRoleClick(role: Role) {
    this.assignedRoleList.push(role);
    this.freeRoleList.splice(this.freeRoleList.indexOf(role), 1);
  }

  onDeleteRoleClick(role: Role) {
    this.assignedRoleList.splice(this.assignedRoleList.indexOf(role), 1);
    this.freeRoleList.push(role);
  }

  entityIsCreated: boolean = true;

  /*
  Настройка данных модального окна при редактировании записи
   */
  onUpdateClick() {
    this.entityIsCreated = false;
    this.prepareFreeRoleList();

    this.formGroup.patchValue({
      lastName: this.individual.fullNameGroup.lastName,
      firstName: this.individual.fullNameGroup.firstName,
      middleName: this.individual.fullNameGroup.middleName,
      address: this.individual.address,
      roleSet: this.individual.roleSet,
    });
    if (this.individual.isaRussianFederationCitizen) {
      this.formGroup.patchValue({
        ru_series: this.individual.passportDetails.passportDetailsRussianFederation.series,
        ru_number: this.individual.passportDetails.passportDetailsRussianFederation.number,
        ru_dateIssue: this.dateFormat(
          this.individual.passportDetails.passportDetailsRussianFederation.dateIssue
        ),
      });
    } else {
      this.formGroup.patchValue({
        foreign_docName: this.individual.passportDetails.documentDetailsForeign.docName,
        foreign_series: this.individual.passportDetails.documentDetailsForeign.series,
        foreign_number: this.individual.passportDetails.documentDetailsForeign.number,
        foreign_dateIssue: this.dateFormat(
          this.individual.passportDetails.documentDetailsForeign.dateIssue
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
    this.freeRoleList = this.roleList;
    this.assignedRoleList = [];
  }

  get f() {
    return this.formGroup.controls;
  }

  saveIndividual() {
    let individual: Individual = {
      uuid: this.entityIsCreated ? '' : this.individual.uuid,
      fullNameGroup: {
        lastName: this.f['lastName'].value,
        firstName: this.f['firstName'].value,
        middleName: this.f['middleName'].value
      },
      address: this.f['address'].value,
      isaRussianFederationCitizen: this.entityIsCreated ?
        this.f['isaRussianFederationCitizen'].value :
        this.individual.isaRussianFederationCitizen,
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
      roleSet: this.assignedRoleList
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
    this.service.deleteIndividualById(this.individual.uuid).subscribe({
      next: _ => {
        this.getAllIndividuals();
      },
      error: err => console.log(err)
    });
  }

  private dateFormat(d: Date): string {
    let date = new Date(d);
    let year: string = date.getFullYear().toString();
    let month: string = (date.getMonth() + 1).toString().padStart(2, '0');
    let day: string = date.getDate().toString().padStart(2, '0');
    return year + "-" + month + "-" + day
  }

}
