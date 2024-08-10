import {Component, OnInit} from '@angular/core';
import {LegalEntityService} from "../legal-entity.service";
import {LegalEntity} from "../../models/LegalEntity";
import {FormBuilder, FormGroup, ReactiveFormsModule, Validators} from "@angular/forms";
import {Sro} from "../../models/Sro";
import {Role} from "../../models/Role";
import {NgForOf, NgIf} from "@angular/common";

@Component({
  selector: 'app-legal-entity-list',
  standalone: true,
  imports: [
    NgForOf,
    NgIf,
    ReactiveFormsModule
  ],
  templateUrl: './legal-entity-list.component.html',
  styleUrl: './legal-entity-list.component.css'
})
export class LegalEntityListComponent implements OnInit {

  legalEntityList: LegalEntity[] = [];
  formGroup: FormGroup;

  constructor(private service: LegalEntityService,
              private fb: FormBuilder) {
    this.formGroup = this.fb.group({
      fullName: ["", Validators.required],
      shortName: ["", Validators.required],
      ogrn: ["", Validators.required],
      inn: ["", Validators.required],
      address: ["", Validators.required],
      phone: [""],
      sro:  [null],
    })
  }

  ngOnInit() {
    this.getLegalEntityList();
    this.getRoleList();
  }

  getLegalEntityList() {
    this.service.getAllLegalEntities().subscribe({
      next: value => {
        this.legalEntityList = value;
      },
      error: err => console.log(err)
    });
  }

  legalEntity: LegalEntity = {
    uuid: "",
    fullName: "",
    shortName: "",
    ogrn: "",
    inn: "",
    address: "",
    phone: "",
    sro: {
      uuid: "",
      name: "",
      inn: "",
      ogrn: "",
    },
    roleSet: []
  }

  legalEntityDetails(le: LegalEntity) {
    this.legalEntity = le;
    this.isSroMember = this.legalEntity.sro != null;
  }

  // Role methods
  roleList: Role[] = [];
  private getRoleList() {
    this.service.getRoleList().subscribe({
      next: value => {
        this.roleList = value;
      },
      error: err => console.log(err)
    });
  }

  freeRoleList: Role[] = [];
  assignedRoleList: Role[] = [];
  private prepareFreeRoleList() {
    this.assignedRoleList = this.legalEntity.roleSet;
    this.freeRoleList = this.roleList.filter(role => {
      for (let r of this.legalEntity.roleSet) {
        if (r.uuid == role.uuid) {
          return false;
        }
      }
      return true;
    });
  }

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

  onUpdateClick() {
    this.entityIsCreated = false;
    this.prepareFreeRoleList();
    this.isSroMember = (this.legalEntity.sro != null);
    this.getSroList();
    this.formGroup.patchValue({
      fullName: this.legalEntity.fullName,
      shortName: this.legalEntity.shortName,
      ogrn: this.legalEntity.ogrn,
      inn: this.legalEntity.inn,
      address: this.legalEntity.address,
      phone: this.legalEntity.phone,
      sro: this.legalEntity.sro,
      roleSet: this.legalEntity.roleSet,
    });
  }

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

  saveLegalEntity() {
    let legalEntity: LegalEntity = {
      uuid: this.entityIsCreated? "" : this.legalEntity.uuid,
      fullName: this.f["fullName"].value,
      shortName: this.f["shortName"].value,
      ogrn: this.f["ogrn"].value,
      inn: this.f["inn"].value,
      address: this.f["address"].value,
      phone: this.f["phone"].value,
      sro: this.f["sro"].value,
      roleSet: this.assignedRoleList
    }
    if (this.entityIsCreated) {
      this.service.createLegalEntity(legalEntity).subscribe({
        next: value => {
          this.legalEntityList.unshift(value);
        },
        error: err => console.log(err)
      });
    } else {
      this.service.updateLegalEntity(legalEntity).subscribe({
        next: value => {
          this.legalEntityList.splice(this.legalEntityList.indexOf(value), 1, value);
        },
        error: err => console.log(err)
      });
    }
  }

  deleteLegalEntity() {
    this.service.deleteLegalEntityById(this.legalEntity.uuid).subscribe({
      next: _ => {
        this.getLegalEntityList();
      },
      error: err => console.log(err)
    });
  }
}
