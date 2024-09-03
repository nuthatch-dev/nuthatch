import { Injectable } from '@angular/core';
import {environment} from "../../../environment";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Individual} from "../../../reference-book/organization-and-representative/models/Individual";
import {
  IndividualEntrepreneur
} from "../../../reference-book/organization-and-representative/models/IndividualEntrepreneur";
import {LegalEntity} from "../../../reference-book/organization-and-representative/models/LegalEntity";

@Injectable({
  providedIn: 'root'
})
export class CounterpartiesService {

  private INDIVIDUAL_BASE_URL: string = environment.BASE_API_URL
    + "/organization-and-representative/api/v1/individual";
  private INDIVIDUAL_ENTREPRENEUR_BASE_URL: string = environment.BASE_API_URL
    + "/organization-and-representative/api/v1/individual-entrepreneur";
  private LEGAL_ENTITY_BASE_URL: string = environment.BASE_API_URL
    + "/organization-and-representative/api/v1/legal-entity";

  constructor(private http: HttpClient) { }

  /*
  Методы для ФЛ
   */
  createIndividual(individual: Individual): Observable<Individual> {
    return this.http.post<Individual>(`${this.INDIVIDUAL_BASE_URL}`, individual);
  }

  getIndividualListByRole(role: string): Observable<Individual[]> {
    return this.http.get<Individual[]>(`${this.INDIVIDUAL_BASE_URL}/all-by-role?role=${role}`)
  }

  /*
  Методы для ИП
   */
  createIndividualEntrepreneur(ie: IndividualEntrepreneur): Observable<IndividualEntrepreneur> {
    return this.http.post<IndividualEntrepreneur>(`${this.INDIVIDUAL_ENTREPRENEUR_BASE_URL}`, ie);
  }

  getIndividualEntrepreneurListByRole(role: string): Observable<IndividualEntrepreneur[]> {
    return this.http
      .get<IndividualEntrepreneur[]>(`${this.INDIVIDUAL_ENTREPRENEUR_BASE_URL}/all-by-role?role=${role}`)
  }

  /*
   Методы для ЮЛ
   */
  createLegalEntity(legalEntity: LegalEntity): Observable<LegalEntity> {
    return this.http.post<LegalEntity>(`${this.LEGAL_ENTITY_BASE_URL}`, legalEntity);
  }

  getLegalEntityListByRole(role: string): Observable<LegalEntity[]> {
    return this.http.get<LegalEntity[]>(`${this.LEGAL_ENTITY_BASE_URL}/all-by-role?role=${role}`)
  }

}