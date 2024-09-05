import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, ReactiveFormsModule, Validators} from "@angular/forms";
import {SroService} from "../sro.service";
import {Sro} from "../../../../models/representative/Sro";
import {NgForOf, NgIf} from "@angular/common";

@Component({
  selector: 'app-sro-list',
  standalone: true,
  imports: [
    NgForOf,
    ReactiveFormsModule,
    NgIf
  ],
  templateUrl: './sro-list.component.html',
  styleUrl: './sro-list.component.css'
})
export class SroListComponent implements OnInit {

  sroList: Sro[] = [];
  formGroup: FormGroup;

  constructor(private service: SroService,
              private fb: FormBuilder) {

    this.formGroup = this.fb.group({
      name: ["", Validators.required],
      inn: ["", Validators.required],
      ogrn: ["", Validators.required],
    });
  }

  ngOnInit() {
    this.getSroList();
  }

  private getSroList() {
    this.service.getAllSro().subscribe({
      next: value => {
        this.sroList = value;
      },
      error: err => console.log(err)
    });
  }

  sro: Sro = {
    uuid: "",
    name: "",
    inn: "",
    ogrn: "",
  }

  sroDetails(sro: Sro) {
    this.sro = sro;
  }

  entityIsCreated: boolean = true;

  onUpdateClick() {
    this.entityIsCreated = false;
    this.formGroup.patchValue({
      name: this.sro.name,
      inn: this.sro.inn,
      ogrn: this.sro.ogrn,
    });
  }

  get f() {
    return this.formGroup.controls;
  }

  saveSro() {

    let sro: Sro = {
      uuid: this.entityIsCreated? "" : this.sro.uuid,
      name: this.f['name'].value,
      inn: this.f['inn'].value,
      ogrn: this.f['ogrn'].value,
    };

    if (this.entityIsCreated) {
      this.service.createSro(sro).subscribe({
        next: value => {
          this.sroList.unshift(value);
        },
        error: err => console.log(err)
      });
    } else {
      this.service.updateSro(sro).subscribe({
        next: _ => {
          this.getSroList();
        },
        error: err => console.log(err)
      });
    }
  }

  onCreateClick() {
    this.entityIsCreated = true;
    this.formGroup.reset();
  }

  deleteSro() {
    this.service.deleteSroById(this.sro.uuid).subscribe({
      next: _ => {
        this.getSroList();
      },
      error: err => console.log(err)
    });
  }

}
