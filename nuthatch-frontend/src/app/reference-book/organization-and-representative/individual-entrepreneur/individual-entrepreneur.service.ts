import { Injectable } from '@angular/core';
import {environment} from "../../../environment";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {IndividualEntrepreneur} from "../../../models/representative/IndividualEntrepreneur";
import {Sro} from "../../../models/representative/Sro";

@Injectable({
  providedIn: 'root'
})
export class IndividualEntrepreneurService {

  private BASE_URL: string = environment.BASE_API_URL +
    "/organization-and-representative/api/v1/individual-entrepreneur";
  private SRO_SERVICE_URL: string = environment.BASE_API_URL +
    "/organization-and-representative/api/v1/sro";

  constructor(private http: HttpClient) { }

  createIndividualEntrepreneur(ie: IndividualEntrepreneur): Observable<IndividualEntrepreneur> {
    return this.http.post<IndividualEntrepreneur>(`${this.BASE_URL}`, ie);
  }

  getIndividualEntrepreneurById(id: string): Observable<IndividualEntrepreneur> {
    return this.http.get<IndividualEntrepreneur>(`${this.BASE_URL}?id=${id}`);
  }

  getIndividualEntrepreneurByRole(id: string): Observable<IndividualEntrepreneur[]> {
    return this.http.get<IndividualEntrepreneur[]>(`${this.BASE_URL}/all-by-role-id?id=${id}`);
  }

  getAllIndividualEntrepreneurs(): Observable<IndividualEntrepreneur[]> {
    return this.http.get<IndividualEntrepreneur[]>(`${this.BASE_URL}/all`);
  }

  updateIndividualEntrepreneur(ie: IndividualEntrepreneur): Observable<IndividualEntrepreneur> {
    return this.http.put<IndividualEntrepreneur>(`${this.BASE_URL}`, ie);
  }

  deleteIndividualEntrepreneurById(id: string): Observable<any> {
    return this.http.delete<any>(`${this.BASE_URL}?id=${id}`);
  }

  getSroList(): Observable<Sro[]> {
    return this.http.get<Sro[]>(`${this.SRO_SERVICE_URL}/all`)
  }
}
