import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {environment} from "../../../environment";
import {Observable} from "rxjs";
import {Representative} from "../models/Representative";
import {IndividualEntrepreneur} from "../models/IndividualEntrepreneur";
import {LegalEntity} from "../models/LegalEntity";

@Injectable({
  providedIn: 'root'
})
export class RepresentativeService {

  private BASE_URL: string = environment.BASE_API_URL +
    "/organization-and-representative/api/v1/representative";
  private ENTREPRENEUR_SERVICE_URL: string = environment.BASE_API_URL +
    "/organization-and-representative/api/v1/individual-entrepreneur";
  private LEGAL_ENTITY_SERVICE_URL: string = environment.BASE_API_URL +
    "/organization-and-representative/api/v1/legal-entity";

  constructor(private http: HttpClient) { }

  createRepresentative(representative: Representative): Observable<Representative> {
    return this.http.post<Representative>(`${this.BASE_URL}`, representative);
  }

  getRepresentativeById(id: string): Observable<Representative> {
    return this.http.get<Representative>(`${this.BASE_URL}?id=${id}`);
  }

  getRepresentativeByRole(id: string): Observable<Representative[]> {
    return this.http.get<Representative[]>(`${this.BASE_URL}/all-by-role-id?id=${id}`);
  }

  getAllRepresentatives(): Observable<Representative[]> {
    return this.http.get<Representative[]>(`${this.BASE_URL}/all`);
  }

  updateRepresentative(representative: Representative): Observable<Representative> {
    return this.http.put<Representative>(`${this.BASE_URL}`, representative);
  }

  deleteRepresentativeById(id: string): Observable<any> {
    return this.http.delete<any>(`${this.BASE_URL}?id=${id}`);
  }

  getIndividualEntrepreneurList(): Observable<IndividualEntrepreneur[]> {
    return this.http.get<IndividualEntrepreneur[]>(`${this.ENTREPRENEUR_SERVICE_URL}/all`);
  }

  getLegalEntityList(): Observable<LegalEntity[]> {
    return this.http.get<LegalEntity[]>(`${this.LEGAL_ENTITY_SERVICE_URL}/all`);
  }

}
