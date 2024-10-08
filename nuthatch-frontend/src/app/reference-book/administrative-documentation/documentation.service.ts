import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {environment} from "../../environment";
import {Node} from "../../models/administrative-document/Node";
import {Observable} from "rxjs";
import {CustomDocument} from "../../models/administrative-document/CustomDocument";
import {AttachedFile} from "../../models/administrative-document/AttachedFile";

@Injectable({
  providedIn: 'root'
})
export class DocumentationService {

  private BASE_URL: string = environment.BASE_API_URL + "/documentation-service/api/v1/node";
  private DOCUMENT_BASE_URL: string = environment.BASE_API_URL + "/documentation-service/api/v1/custom-document";
  private ATTACHE_BASE_URL: string = environment.BASE_API_URL + "/documentation-service/api/v1/attached-file";

  constructor(private http: HttpClient) {
  }

  createNode(node: Node): Observable<Node> {
    return this.http.post<Node>(`${this.BASE_URL}`, node);
  }

  getNodeById(id: string): Observable<Node> {
    return this.http.get<Node>(`${this.BASE_URL}?id=${id}`);
  }

  getNodeListByParentNodeId(id: string): Observable<Node[]> {
    return this.http.get<Node[]>(`${this.BASE_URL}/all-by-parent?id=${id}`)
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

  updateDocument(document: CustomDocument): Observable<CustomDocument> {
    return this.http.put<CustomDocument>(`${this.DOCUMENT_BASE_URL}`, document);
  }

  deleteDocumentById(id: string): Observable<any> {
    return this.http.delete<any>(`${this.DOCUMENT_BASE_URL}?id=${id}`);
  }

  uploadFile(file: File): Observable<AttachedFile> {
    const formData: FormData = new FormData();
    formData.append('file', file, file.name);
    return this.http.post<AttachedFile>(`${this.ATTACHE_BASE_URL}/upload`, formData)
  }
}
