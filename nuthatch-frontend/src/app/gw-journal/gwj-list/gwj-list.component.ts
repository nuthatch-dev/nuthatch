import {Component, OnInit} from '@angular/core';
import {GwJournalService} from "../gw-journal.service";
import {GeneralWorkJournal} from "../models/GeneralWorkJournal";
import {Router} from "@angular/router";
import {DatePipe, NgForOf, NgIf} from "@angular/common";

@Component({
  selector: 'app-gwj-list',
  standalone: true,
  imports: [
    NgForOf,
    DatePipe,
    NgIf
  ],
  templateUrl: './gwj-list.component.html',
  styleUrl: './gwj-list.component.css'
})
export class GwjListComponent implements OnInit {

  generalWorkJournalList: GeneralWorkJournal[] = [];

  constructor(private service: GwJournalService,
              private router: Router) {
  }

  ngOnInit(): void {
    this.getGeneralWorkJournalList();
  }

  private getGeneralWorkJournalList() {
    this.service.getAllGeneralWorkJournals().subscribe({
      next: value => {
        console.log(value);
        this.generalWorkJournalList = value;
      },
      error: err => console.log(err)
    });
  }

  generalWorkJournalDetails(id: string) {
    this.service.getGeneralWorkJournalById(id).subscribe({
      next: value => {
        this.router.navigate(['/gwj-details', id]);
      },
      error: err => console.log(err)
    });
  }

}
