import {Component, OnInit} from '@angular/core';
import {IndividualService} from "../individual.service";
import {Router} from "@angular/router";
import {Individual} from "../../models/Individual";
import {DatePipe, NgForOf, NgIf} from "@angular/common";

@Component({
  selector: 'app-individual-list',
  standalone: true,
  imports: [
    NgForOf,
    NgIf,
    DatePipe
  ],
  templateUrl: './individual-list.component.html',
  styleUrl: './individual-list.component.css'
})
export class IndividualListComponent implements OnInit {

  individualList: Individual[] = [];

  constructor(private service: IndividualService,
              private router: Router) {
  }

  ngOnInit(): void {
    this.getAllIndividuals();
  }

  private getAllIndividuals() {
    this.service.getAllIndividuals().subscribe({
      next: value => {
        this.individualList = value;
        console.log(this.individualList);
      },
      error: err => console.log(err)
    });
  }

  individualDetails(id: string) {
    this.router.navigate(['/individual-details', id]);
  }

}
