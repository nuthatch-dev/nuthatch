import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {environment} from "../environment";
import {GeneralWorkJournal} from "./models/GeneralWorkJournal";
import {Observable} from "rxjs";
import {TitleChangeDto} from "./models/TitleChangeDto";

@Injectable({
  providedIn: 'root'
})
export class GwJournalService {

  private BASE_URL: string = environment.BASE_API_URL + '/general-work-journal/api/v1';

  constructor(private http: HttpClient) { }

  createGeneralWorkJournal(gwj: GeneralWorkJournal): Observable<GeneralWorkJournal> {
    return this.http.post<GeneralWorkJournal>(`${this.BASE_URL}/general-work-journal`, gwj);
  }

  getGeneralWorkJournalById(id: string): Observable<GeneralWorkJournal> {
    return this.http.get<GeneralWorkJournal>(`${this.BASE_URL}/general-work-journal/get-by-id?id=${id}`);
  }

  getAllGeneralWorkJournals(): Observable<GeneralWorkJournal[]> {
    return this.http.get<GeneralWorkJournal[]>(`${this.BASE_URL}/general-work-journal/all`);
  }

  getArchivedGeneralWorkJournals(): Observable<GeneralWorkJournal[]> {
    return this.http.get<GeneralWorkJournal[]>(`${this.BASE_URL}/general-work-journal/archived`);
  }

  changeArchivedAttribute(id: string): Observable<boolean> {
    return this.http.patch<boolean>(`${this.BASE_URL}/general-work-journal/set-archived`, id)
  }

  getTitleChanges(id: string): Observable<TitleChangeDto[]> {
    return this.http.get<TitleChangeDto[]>(`${this.BASE_URL}/general-work-journal/title-changes`)
  }


}
