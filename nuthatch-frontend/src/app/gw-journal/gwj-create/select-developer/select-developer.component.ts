import {Component, OnInit} from '@angular/core';
import {Individual} from "../../../reference-book/organization-and-representative/models/Individual";
import {
  IndividualEntrepreneur
} from "../../../reference-book/organization-and-representative/models/IndividualEntrepreneur";
import {LegalEntity} from "../../../reference-book/organization-and-representative/models/LegalEntity";
import {CounterpartiesService} from "../common/counterparties.service";
import {NgIf} from "@angular/common";
import {ReactiveFormsModule} from "@angular/forms";
import {CreateCounterpartyComponent} from "../common/create-counterparty/create-counterparty.component";
import {CounterpartyType} from "../common/counterparty-type";

@Component({
  selector: 'app-select-developer',
  standalone: true,
  imports: [
    NgIf,
    ReactiveFormsModule,
    CreateCounterpartyComponent
  ],
  templateUrl: './select-developer.component.html',
  styleUrl: './select-developer.component.css'
})
export class SelectDeveloperComponent implements OnInit {

  individualList: Individual[] = [];
  individualEntrepreneurList: IndividualEntrepreneur[] = [];
  legalEntityList: LegalEntity[] = [];
  protected readonly CounterpartyType = CounterpartyType;

  constructor(private service: CounterpartiesService) {
  }

  ngOnInit(): void {
    this.getIndividualList();
    this.getIndividualEntrepreneurList();
    this.getLegalEntityList();
  }

  private ROLE: string = "DEVELOPER";

  private getIndividualList() {
    this.service.getIndividualListByRole(this.ROLE).subscribe({
      next: value => {
        this.individualList = value;
      },
      error: err => console.log(err)
    });
  }

  private getIndividualEntrepreneurList() {
    this.service.getIndividualEntrepreneurListByRole(this.ROLE).subscribe({
      next: value => {
        this.individualEntrepreneurList = value;
      },
      error: err => console.log(err)
    });
  }

  private getLegalEntityList() {
    this.service.getLegalEntityListByRole(this.ROLE).subscribe({
      next: value => {
        this.legalEntityList = value;
      },
      error: err => console.log(err)
    });
  }

  counterpartyCreatedHidden: boolean = true;

  developerAsideHidden: boolean = true;

  showDeveloperList() {
    this.developerAsideHidden = !this.developerAsideHidden;
  }

  createdCounterPartyType: CounterpartyType | null = null;

  createNewCounterparty(counterpartyType: CounterpartyType) {
    this.createdCounterPartyType = counterpartyType;
    this.developerAsideHidden = true;
    this.counterpartyCreatedHidden = false;
  }

  onCounterpartyCreated(isCreated: boolean) {
    this.counterpartyCreatedHidden = true;
    this.developerAsideHidden = false;
    if (isCreated) {
      switch (this.createdCounterPartyType) {
        case CounterpartyType.INDIVIDUAL:
          this.getIndividualList();
          break;
        case CounterpartyType.INDIVIDUAL_ENTREPRENEUR:
          this.getIndividualEntrepreneurList();
          break;
        case CounterpartyType.LEGAL_ENTITY:
          this.getLegalEntityList();
          break;
      }
    }
  }

}
