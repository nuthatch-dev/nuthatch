import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {environment} from "../../../environment";
import {Observable} from "rxjs";
import {Representative} from "../../../models/representative/Representative";
import {IndividualEntrepreneur} from "../../../models/representative/IndividualEntrepreneur";
import {LegalEntity} from "../../../models/representative/LegalEntity";
import {Node} from "../../../models/administrative-document/Node";

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
  private DOCUMENT_NODE_BASE_URL: string = environment.BASE_API_URL +
    "/documentation-service/api/v1/node";

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

  getNodeById(id: string): Observable<Node> {
    return this.http.get<Node>(`${this.DOCUMENT_NODE_BASE_URL}?id=${id}`);
  }

  getNodeListByParentNodeId(id: string): Observable<Node[]> {
    return this.http.get<Node[]>(`${this.DOCUMENT_NODE_BASE_URL}/all-by-parent?id=${id}`)
  }

}
