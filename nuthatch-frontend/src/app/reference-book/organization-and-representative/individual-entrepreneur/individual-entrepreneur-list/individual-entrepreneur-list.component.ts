import {Component, OnInit} from '@angular/core';
import {IndividualEntrepreneur} from "../../models/IndividualEntrepreneur";
import {FormBuilder, FormGroup, ReactiveFormsModule, Validators} from "@angular/forms";
import {IndividualEntrepreneurService} from "../individual-entrepreneur.service";
import {Role} from "../../models/Role";
import {DatePipe, NgForOf, NgIf} from "@angular/common";
import {Sro} from "../../models/Sro";

@Component({
  selector: 'app-individual-entrepreneur-list',
  standalone: true,
  imports: [
    DatePipe,
    NgForOf,
    NgIf,
    ReactiveFormsModule
  ],
  templateUrl: './individual-entrepreneur-list.component.html',
  styleUrl: './individual-entrepreneur-list.component.css'
})
export class IndividualEntrepreneurListComponent implements OnInit {

  ieList: IndividualEntrepreneur[] = [];
  formGroup: FormGroup;

  constructor(private service: IndividualEntrepreneurService,
              private fb: FormBuilder) {

    this.formGroup = this.fb.group({
      lastName: ["", Validators.required],
      firstName: ["", Validators.required],
      middleName: [""],
      address: ["", Validators.required],
      ogrnip: ["", Validators.required],
      inn: ["", Validators.required],
      sro: [null],
    });

  }

  ngOnInit() {
    this.getIeList();
    this.getRoleList();
  }

  private getIeList() {
    this.service.getAllIndividualEntrepreneurs().subscribe({
      next: value => {
        this.ieList = value;
      },
      error: err => console.log(err)
    });
  }

  ie: IndividualEntrepreneur = {
    uuid: "",
    fullNameGroup: {
      lastName: "",
      firstName: "",
      middleName: ""
    },
    address: "",
    ogrnip: "",
    inn: "",
    sro: {
      uuid: "",
      name: "",
      inn: "",
      ogrn: "",
    },
    roleSet: []
  }

  ieDetails(ie: IndividualEntrepreneur) {
    this.ie = ie;
    this.isSroMember = this.ie.sro != null;
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
    this.assignedRoleList = this.ie.roleSet;
    this.freeRoleList = this.roleList.filter(role => {
      for (let r of this.ie.roleSet) {
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
  sroList: Sro[] = [];
  isSroMember: boolean = false;

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

  /*
  Настройка данных модального окна при редактировании записи
   */
  onUpdateClick() {
    this.entityIsCreated = false;
    this.prepareFreeRoleList();
    this.isSroMember = (this.ie.sro != null);
    this.getSroList();
    this.formGroup.patchValue({
      lastName: this.ie.fullNameGroup.lastName,
      firstName: this.ie.fullNameGroup.firstName,
      middleName: this.ie.fullNameGroup.middleName,
      address: this.ie.address,
      ogrnip: this.ie.ogrnip,
      inn: this.ie.inn,
      sro: this.ie.sro,
      roleSet: this.ie.roleSet,
    });
  }

  /*
  Настройка данных модального окна при создании новой записи
   */
  onCreateClick() {
    this.entityIsCreated = true;
    this.freeRoleList = this.roleList;
    this.assignedRoleList = [];
    this.isSroMember = false;
    this.getSroList();
    this.formGroup.reset();
  }

  get f() {
    return this.formGroup.controls;
  }

  saveIndividualEntrepreneur() {
    let ie: IndividualEntrepreneur = {
      uuid: this.entityIsCreated ? "" : this.ie.uuid,
      fullNameGroup: {
        lastName: this.f["lastName"].value,
        firstName: this.f["firstName"].value,
        middleName: this.f["middleName"].value
      },
      address: this.f["address"].value,
      ogrnip: this.f["ogrnip"].value,
      inn: this.f["inn"].value,
      sro: this.f["sro"].value,
      roleSet: this.assignedRoleList
    };
    if (this.entityIsCreated) {
      this.service.createIndividualEntrepreneur(ie).subscribe({
        next: value => {
          this.ieList.unshift(value);
        },
        error: err => console.log(err)
      });
    } else {
      this.service.updateIndividualEntrepreneur(ie).subscribe({
        next: _ => {
          this.getIeList();
        },
        error: err => console.log(err)
      });
    }
  }

  deleteIndividualEntrepreneur() {
    this.service.deleteIndividualEntrepreneurById(this.ie.uuid).subscribe({
      next: _ => {
        this.getIeList();
      },
      error: err => console.log(err)
    });
  }
}
