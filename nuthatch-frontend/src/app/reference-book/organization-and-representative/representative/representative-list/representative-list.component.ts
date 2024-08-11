import {Component, OnInit} from '@angular/core';
import {RepresentativeService} from "../representative.service";
import {Representative} from "../../models/Representative";
import {FormBuilder, FormGroup, FormsModule, ReactiveFormsModule, Validators} from "@angular/forms";
import {LegalEntity} from "../../models/LegalEntity";
import {IndividualEntrepreneur} from "../../models/IndividualEntrepreneur";
import {NgForOf, NgIf} from "@angular/common";

@Component({
  selector: 'app-representative-list',
  standalone: true,
  imports: [
    FormsModule,
    NgForOf,
    NgIf,
    ReactiveFormsModule
  ],
  templateUrl: './representative-list.component.html',
  styleUrl: './representative-list.component.css'
})
export class RepresentativeListComponent implements OnInit {

  representativeList: Representative[] = [];
  individualEntrepreneurList: IndividualEntrepreneur[] = [];
  legalEntityList: LegalEntity[] = [];
  formGroup: FormGroup;

  constructor(private service: RepresentativeService,
              private fb: FormBuilder) {
    this.formGroup = this.fb.group({
      lastName: ["", Validators.required],
      firstName: ["", Validators.required],
      middleName: [""],
      legalEntity: [null],
      individualEntrepreneur: [null],
      position: ["", Validators.required],
      nostroyNumber: [""],
      administrativeDocument: ["", Validators.required],
    });
  }

  ngOnInit() {
    this.getRepresentativeList();
    this.getIndividualEntrepreneurList();
    this.getLegalEntityList();
  }

  private getRepresentativeList() {
    this.service.getAllRepresentatives().subscribe({
      next: value => {
        this.representativeList = value;
      },
      error: err => console.log(err)
    });
  }

  private getIndividualEntrepreneurList() {
    this.service.getIndividualEntrepreneurList().subscribe({
      next: value => {
        this.individualEntrepreneurList = value;
      },
      error: err => console.log(err)
    });
  }

  private getLegalEntityList() {
    this.service.getLegalEntityList().subscribe({
      next: value => {
        this.legalEntityList = value;
      },
      error: err => console.log(err)
    });
  }

  representativeDetails(representative: Representative) {
    this.representative = representative;
  }

  entityIsCreated: boolean = true;

  /*
  Настройка данных модального окна при редактировании записи
   */
  onUpdateClick() {
    this.entityIsCreated = false;
    this.formGroup.patchValue({
      lastName: this.representative.fullNameGroup.lastName,
      firstName: this.representative.fullNameGroup.firstName,
      middleName: this.representative.fullNameGroup.middleName,
      legalEntity: this.representative.legalEntity,
      individualEntrepreneur: this.representative.individualEntrepreneur,
      position: this.representative.position,
      nostroyNumber: this.representative.nostroyNumber,
      administrativeDocument: this.representative.administrativeDocument,
    });
  }

  /*
  Настройка данных модального окна при создании новой записи
  */
  onCreateClick() {
    this.entityIsCreated = true;
    this.formGroup.reset();
  }

  get f() {
    return this.formGroup.controls;
  }

  saveRepresentative() {
    let representative: Representative = {
      uuid: this.entityIsCreated? "" : this.representative.uuid,
      fullNameGroup: {
        lastName: this.f["lastName"].value,
        firstName: this.f["firstName"].value,
        middleName: this.f["middleName"].value,
      },
      legalEntity: this.f["legalEntity"].value,
      individualEntrepreneur: this.f["individualEntrepreneur"].value,
      position: this.f["position"].value,
      nostroyNumber: this.f["nostroyNumber"].value,
      administrativeDocument: this.f["administrativeDocument"].value,
    };
    if (this.entityIsCreated) {
      this.service.createRepresentative(representative).subscribe({
        next: value => {
          this.representativeList.unshift(value);
        },
        error: err => console.log(err)
      });
    } else {
      this.service.updateRepresentative(representative).subscribe({
        next: _ => {
          this.getRepresentativeList();
        },
        error: err => console.log(err)
      });
    }
  }

  deleteRepresentative() {
    this.service.deleteRepresentativeById(this.representative.uuid).subscribe({
      next: _ => {
        this.getRepresentativeList();
      },
      error: err => console.log(err)
    });
  }


  representative: Representative = {
    uuid: '',
    fullNameGroup: {
      lastName: '',
      firstName: '',
      middleName: '',
    },
    legalEntity: {
      uuid: '',
      fullName: '',
      shortName: '',
      inn: '',
      ogrn: '',
      address: '',
      phone: '',
      sro: {
        uuid: '',
        name: '',
        inn: '',
        ogrn: ''
      },
      roleSet: [],
    },
    individualEntrepreneur: {
      uuid: '',
      fullNameGroup: {
        lastName: '',
        firstName: '',
        middleName: ''
      },
      address: '',
      ogrnip: '',
      inn: '',
      sro: {
        uuid: '',
        name: '',
        inn: '',
        ogrn: ''
      },
      roleSet: []
    },
    position: '',
    nostroyNumber: '',
    administrativeDocument: '',
  };
}
