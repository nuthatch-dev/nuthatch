import { Injectable } from '@angular/core';
import {environment} from "../../../environment";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Sro} from "../models/Sro";
import {LegalEntity} from "../models/LegalEntity";

@Injectable({
  providedIn: 'root'
})
export class LegalEntityService {

  private BASE_URL: string = environment.BASE_API_URL +
    "/organization-and-representative/api/v1/legal-entity";
  private SRO_SERVICE_URL: string = environment.BASE_API_URL +
    "/organization-and-representative/api/v1/sro";

  constructor(private http: HttpClient) { }

  createLegalEntity(legalEntity: LegalEntity): Observable<LegalEntity> {
    return this.http.post<LegalEntity>(`${this.BASE_URL}`, legalEntity);
  }

  getLegalEntityById(id: string): Observable<LegalEntity> {
    return this.http.get<LegalEntity>(`${this.BASE_URL}?id=${id}`);
  }

  getLegalEntityByRole(id: string): Observable<LegalEntity[]> {
    return this.http.get<LegalEntity[]>(`${this.BASE_URL}/all-by-role-id?id=${id}`);
  }

  getAllLegalEntities(): Observable<LegalEntity[]> {
    return this.http.get<LegalEntity[]>(`${this.BASE_URL}/all`);
  }

  updateLegalEntity(legalEntity: LegalEntity): Observable<LegalEntity> {
    return this.http.put<LegalEntity>(`${this.BASE_URL}`, legalEntity);
  }

  deleteLegalEntityById(id: string): Observable<any> {
    return this.http.delete<any>(`${this.BASE_URL}?id=${id}`);
  }

  getSroList(): Observable<Sro[]> {
    return this.http.get<Sro[]>(`${this.SRO_SERVICE_URL}/all`)
  }
}
