import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {environment} from "../../../environment";
import {Role} from "../models/Role";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class RoleService {

  private BASE_URL: string = environment.BASE_API_URL + "/organization-and-representative/api/v1/role";

  constructor(private http: HttpClient) { }

  createRole(role: Role): Observable<Role> {
    return this.http.post<Role>(`${this.BASE_URL}`, role);
  }

  getRoleById(id: string): Observable<Role> {
    return this.http.get<Role>(`${this.BASE_URL}?id=${id}`)
  }

  getAllRoles(): Observable<Role[]> {
    return this.http.get<Role[]>(`${this.BASE_URL}/all`);
  }

  updateRole(role: Role): Observable<Role> {
    return this.http.put<Role>(`${this.BASE_URL}`, role);
  }

  deleteById(id: string): Observable<any> {
    return this.http.delete<any>(`${this.BASE_URL}?id=${id}`);
  }
}
