import {Component, OnInit} from '@angular/core';
import {IndividualEntrepreneur} from "../../models/IndividualEntrepreneur";
import {FormBuilder, FormGroup, ReactiveFormsModule, Validators} from "@angular/forms";
import {IndividualEntrepreneurService} from "../individual-entrepreneur.service";
import {RoleMap} from "../../common/RoleMap";
import {DatePipe, NgForOf, NgIf} from "@angular/common";
import {Sro} from "../../models/Sro";
import {RoleManagement} from "../../common/RoleManagement";

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
export class IndividualEntrepreneurListComponent extends RoleManagement<IndividualEntrepreneur> implements OnInit {

  ieList: IndividualEntrepreneur[] = [];
  formGroup: FormGroup;

  constructor(private service: IndividualEntrepreneurService,
              private fb: FormBuilder) {
    super();
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
  }

  private getIeList() {
    this.service.getAllIndividualEntrepreneurs().subscribe({
      next: value => {
        this.ieList = value;
      },
      error: err => console.log(err)
    });
  }

  override entity: IndividualEntrepreneur = {
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
    this.entity = ie;
    this.isSroMember = this.entity.sro != null;
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
    this.prepareRoleMaps();
    this.isSroMember = (this.entity.sro != null);
    this.getSroList();
    this.formGroup.patchValue({
      lastName: this.entity.fullNameGroup.lastName,
      firstName: this.entity.fullNameGroup.firstName,
      middleName: this.entity.fullNameGroup.middleName,
      address: this.entity.address,
      ogrnip: this.entity.ogrnip,
      inn: this.entity.inn,
      sro: this.entity.sro,
      roleSet: this.entity.roleSet,
    });
  }

  /*
  Настройка данных модального окна при создании новой записи
   */
  onCreateClick() {
    this.entityIsCreated = true;
    this.formGroup.reset();
    // Установка ролей
    this.assignedRoleMap = new Map<string, string>();
    this.freeRoleMap = new Map<string, string>();
    RoleMap.forEach((value, key) => this.freeRoleMap.set(key, value));
    // Начальные данные по СРО
    this.isSroMember = false;
    this.getSroList();
  }

  get f() {
    return this.formGroup.controls;
  }

  saveIndividualEntrepreneur() {
    /*
    Удаляем данные по СРО при отключении isSroMember при редактировании записи
     */
    if (!this.isSroMember) {
      this.formGroup.patchValue({
        sro: null,
      });
    }

    let ie: IndividualEntrepreneur = {
      uuid: this.entityIsCreated ? "" : this.entity.uuid,
      fullNameGroup: {
        lastName: this.f["lastName"].value,
        firstName: this.f["firstName"].value,
        middleName: this.f["middleName"].value
      },
      address: this.f["address"].value,
      ogrnip: this.f["ogrnip"].value,
      inn: this.f["inn"].value,
      sro: this.f["sro"].value,
      roleSet: Array.from(this.assignedRoleMap.keys()),
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
    this.service.deleteIndividualEntrepreneurById(this.entity.uuid).subscribe({
      next: _ => {
        this.getIeList();
      },
      error: err => console.log(err)
    });
  }
}
