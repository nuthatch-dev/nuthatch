import {Component, OnInit} from '@angular/core';
import {LegalEntityService} from "../legal-entity.service";
import {LegalEntity} from "../../models/LegalEntity";
import {FormBuilder, FormGroup, ReactiveFormsModule, Validators} from "@angular/forms";
import {Sro} from "../../models/Sro";
import {RoleMap} from "../../common/RoleMap";
import {NgForOf, NgIf} from "@angular/common";
import {RoleManagement} from "../../common/RoleManagement";

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
export class LegalEntityListComponent extends RoleManagement<LegalEntity> implements OnInit {

  legalEntityList: LegalEntity[] = [];
  formGroup: FormGroup;

  constructor(private service: LegalEntityService,
              private fb: FormBuilder) {
    super();
    this.formGroup = this.fb.group({
      fullName: ["", Validators.required],
      shortName: ["", Validators.required],
      ogrn: ["", Validators.required],
      inn: ["", Validators.required],
      address: ["", Validators.required],
      phone: [""],
      sro: [null],
    })
  }

  ngOnInit() {
    this.getLegalEntityList();
  }

  getLegalEntityList() {
    this.service.getAllLegalEntities().subscribe({
      next: value => {
        this.legalEntityList = value;
      },
      error: err => console.log(err)
    });
  }

  override entity: LegalEntity = {
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
    this.entity = le;
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
      fullName: this.entity.fullName,
      shortName: this.entity.shortName,
      ogrn: this.entity.ogrn,
      inn: this.entity.inn,
      address: this.entity.address,
      phone: this.entity.phone,
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
    this.assignedRoleMap = new Map<string, string>();
    this.freeRoleMap = new Map<string, string>();
    RoleMap.forEach((value, key) => this.freeRoleMap.set(key, value));
    this.isSroMember = false;
    this.getSroList();
  }

  get f() {
    return this.formGroup.controls;
  }

  saveLegalEntity() {
    /*
    Удаляем данные по СРО при отключении isSroMember при редактировании записи
     */
    if (!this.isSroMember) {
      this.formGroup.patchValue({
        sro: null,
      });
    }

    let legalEntity: LegalEntity = {
      uuid: this.entityIsCreated ? "" : this.entity.uuid,
      fullName: this.f["fullName"].value,
      shortName: this.f["shortName"].value,
      ogrn: this.f["ogrn"].value,
      inn: this.f["inn"].value,
      address: this.f["address"].value,
      phone: this.f["phone"].value,
      sro: this.f["sro"].value,
      roleSet: Array.from(this.assignedRoleMap.keys()),
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
        next: _ => {
          this.getLegalEntityList();
        },
        error: err => console.log(err)
      });
    }
  }

  deleteLegalEntity() {
    this.service.deleteLegalEntityById(this.entity.uuid).subscribe({
      next: _ => {
        this.getLegalEntityList();
      },
      error: err => console.log(err)
    });
  }
}
