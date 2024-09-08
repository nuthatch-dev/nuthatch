import {Component, Input, OnChanges, SimpleChanges} from '@angular/core';
import {DeveloperRepresentativeService} from "./developer-representative.service";
import {Representative} from "../../../models/representative/Representative";

@Component({
  selector: 'app-developer-representative',
  standalone: true,
  imports: [],
  templateUrl: './developer-representative.component.html',
  styleUrl: './developer-representative.component.css'
})
export class DeveloperRepresentativeComponent implements OnChanges {

  representativeList: Representative[] = [];

  @Input() developerId: string | undefined;

  constructor(private service: DeveloperRepresentativeService) {
  }

  ngOnChanges(changes: SimpleChanges): void {
    this.service.getRepresentativeListByCounterpartyId(changes["developerId"].currentValue).subscribe({
      next: value => {
        this.representativeList = value;
      },
      error: err => console.log(err)
    });
  }

}
