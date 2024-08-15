import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {environment} from "../../environment";
import {Node} from "./models/Node";
import {Observable} from "rxjs";
import {CustomDocument} from "./models/CustomDocument";

@Injectable({
  providedIn: 'root'
})
export class DocumentationService {

  private BASE_URL: string = environment.BASE_API_URL + "/documentation-service/api/v1/node";
  private DOCUMENT_BASE_URL: string = environment.BASE_API_URL + "/documentation-service/api/v1/custom-document";

  constructor(private http: HttpClient) {
  }

  createNode(node: Node): Observable<Node> {
    return this.http.post<Node>(`${this.BASE_URL}`, node);
  }

  getNodeById(id: string): Observable<Node> {
    return this.http.get<Node>(`${this.BASE_URL}?id=${id}`);
  }

  getNodeList(): Observable<Node[]> {
    return this.http.get<Node[]>(`${this.BASE_URL}/all`);
  }

  updateNode(node: Node): Observable<Node> {
    return this.http.put<Node>(`${this.BASE_URL}`, node);
  }

  deleteNodeById(id: string): Observable<any> {
    return this.http.delete<any>(`${this.BASE_URL}?id=${id}`);
  }

  createCustomDocument(document: CustomDocument): Observable<CustomDocument> {
    return this.http.post<CustomDocument>(`${this.DOCUMENT_BASE_URL}`, document);
  }
}
