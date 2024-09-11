import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {environment} from "../../../environment";
import {Observable} from "rxjs";
import {Representative} from "../../../models/representative/Representative";
import {CustomDocument} from "../../../models/administrative-document/CustomDocument";

@Injectable({
  providedIn: 'root'
})
export class DeveloperRepresentativeService {

  constructor(private http: HttpClient) { }

  private BASE_URL: string = environment.BASE_API_URL +
    "/organization-and-representative/api/v1/representative";
  private DOCUMENT_BASE_URL: string = environment.BASE_API_URL +
    "/documentation-service/api/v1/custom-document";

  getRepresentativeListByCounterpartyId(id: string): Observable<Representative[]> {
    return this.http.get<Representative[]>(`${this.BASE_URL}/all-by-counterparty?id=${id}`);
  }

  createRepresentative(representative: Representative): Observable<Representative> {
    return this.http.post<Representative>(`${this.BASE_URL}`, representative);
  }

  createCustomDocument(document: CustomDocument): Observable<CustomDocument> {
    return this.http.post<CustomDocument>(`${this.DOCUMENT_BASE_URL}`, document);
  }

  getDocumentById(id: string): Observable<CustomDocument> {
    return this.http.get<CustomDocument>(`${this.DOCUMENT_BASE_URL}?id=${id}`);
  }
}
