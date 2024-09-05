import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {environment} from "../../../environment";
import {Sro} from "../../../models/representative/Sro";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class SroService {

  private BASE_URL: string = environment.BASE_API_URL + "/organization-and-representative/api/v1/sro";

  constructor(private http: HttpClient) { }

  createSro(sro: Sro): Observable<Sro> {
    return this.http.post<Sro>(`${this.BASE_URL}`, sro);
  }

  getSroById(id: string): Observable<Sro> {
    return this.http.get<Sro>(`${this.BASE_URL}?id=${id}`);
  }

  getAllSro(): Observable<Sro[]> {
    return this.http.get<Sro[]>(`${this.BASE_URL}/all`);
  }

  updateSro(sro: Sro): Observable<Sro> {
    return this.http.put<Sro>(`${this.BASE_URL}`, sro);
  }

  deleteSroById(id: string): Observable<any> {
    return this.http.delete<any>(`${this.BASE_URL}?id=${id}`);
  }

}
