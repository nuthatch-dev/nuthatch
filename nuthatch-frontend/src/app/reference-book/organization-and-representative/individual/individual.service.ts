import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {environment} from "../../../environment";
import {Individual} from "../models/Individual";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class IndividualService {

  private BASE_URL: string = environment.BASE_API_URL + "/organization-and-representative/api/v1/individual";

  constructor(private http: HttpClient) {
  }

  createIndividual(individual: Individual): Observable<Individual> {
    return this.http.post<Individual>(`${this.BASE_URL}`, individual);
  }

  getIndividualById(id: string): Observable<Individual> {
    return this.http.get<Individual>(`${this.BASE_URL}?id=${id}`);
  }

  getIndividualByRole(id: string): Observable<Individual[]> {
    return this.http.get<Individual[]>(`${this.BASE_URL}/all-by-role-id?id=${id}`);
  }

  getAllIndividuals(): Observable<Individual[]> {
    return this.http.get<Individual[]>(`${this.BASE_URL}/all`);
  }

  updateIndividual(individual: Individual): Observable<Individual> {
    return this.http.put<Individual>(`${this.BASE_URL}`, individual);
  }

  deleteIndividualById(id: string): Observable<any> {
    return this.http.delete<any>(`${this.BASE_URL}?id=${id}`);
  }

}
