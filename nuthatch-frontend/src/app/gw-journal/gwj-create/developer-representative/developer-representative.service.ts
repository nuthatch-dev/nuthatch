import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {environment} from "../../../environment";
import {Observable} from "rxjs";
import {Representative} from "../../../models/representative/Representative";

@Injectable({
  providedIn: 'root'
})
export class DeveloperRepresentativeService {

  constructor(private http: HttpClient) { }

  private BASE_URL: string = environment.BASE_API_URL +
    "/organization-and-representative/api/v1/representative";

  getRepresentativeListByCounterpartyId(id: string): Observable<Representative[]> {
    return this.http.get<Representative[]>(`${this.BASE_URL}/all-by-counterparty?id=${id}`);
  }

}
