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
      roleSet: [[]],
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

  entityIsCreated: boolean = true;
  roleList: Role[] = [];
  sroList: Sro[] = [];
  isSroMember: boolean = false;
  setSroSwitch(value: boolean) {
    this.isSroMember = value;
  }

  private getRoleList() {
    this.service.getRoleList().subscribe({
      next: value => {
        this.roleList = value;
      },
      error: err => console.log(err)
    });
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
    this.getRoleList();
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

  onCreateClick() {
    this.entityIsCreated = true;
    this.getRoleList();
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
      roleSet: this.f["roleSet"].value
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
        next: value => {
          this.ieList.splice(this.ieList.indexOf(value), 1, value);
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
