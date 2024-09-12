import {Component, EventEmitter, Input, Output} from '@angular/core';
import {CreateCounterpartyComponent} from "../create-counterparty/create-counterparty.component";
import {Individual} from "../../../../models/representative/Individual";
import {IndividualEntrepreneur} from "../../../../models/representative/IndividualEntrepreneur";
import {LegalEntity} from "../../../../models/representative/LegalEntity";
import {CounterpartyType} from "../counterparty-type";

@Component({
  selector: 'app-common-select-template',
  standalone: true,
    imports: [
        CreateCounterpartyComponent
    ],
  templateUrl: './common-select-template.component.html',
  styleUrl: './common-select-template.component.css'
})
export class CommonSelectTemplateComponent {

  @Input() individualList: Individual[] = [];
  @Input() individualEntrepreneurList: IndividualEntrepreneur[] = [];
  @Input() legalEntityList: LegalEntity[] = [];

  @Input() displayCounterpartyName: string = "";
  @Input() displayCounterpartySro: string = "";

  @Input() counterpartyAsideHidden: boolean = true;
  @Input() counterpartyCreatedHidden: boolean = true;

  @Input() createdCounterpartyType: CounterpartyType | null = null;
  protected readonly CounterpartyType = CounterpartyType;

  @Output() showCounterpartyListEvent = new EventEmitter<void>();
  showCounterpartyList() {
    this.showCounterpartyListEvent.emit();
  }

  @Output() individualSelectedEvent = new EventEmitter<Individual>();
  individualSelected(i: Individual) {
    this.individualSelectedEvent.emit(i);
  }

  @Output() individualEntrepreneurSelectedEvent
    = new EventEmitter<IndividualEntrepreneur>();
  individualEntrepreneurSelected(ie: IndividualEntrepreneur) {
    this.individualEntrepreneurSelectedEvent.emit(ie);
  }

  @Output() legalEntitySelectedEvent = new EventEmitter<LegalEntity>();
  legalEntitySelected(le: LegalEntity) {
    this.legalEntitySelectedEvent.emit(le);
  }

  @Output() createNewCounterpartyEvent = new EventEmitter<CounterpartyType>();
  createNewCounterparty(ct: CounterpartyType) {
    this.createNewCounterpartyEvent.emit(ct);
  }

  @Output() onCounterpartyCreatedEvent = new EventEmitter<boolean>();
  onCounterpartyCreated(e: boolean) {
    this.onCounterpartyCreatedEvent.emit(e);
  }
}
