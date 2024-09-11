import {Component, OnInit} from '@angular/core';
import {DocumentationService} from "../documentation.service";
import {Node} from "../../../models/administrative-document/Node";
import {FormBuilder, FormGroup, FormsModule, ReactiveFormsModule, Validators} from "@angular/forms";
import {DatePipe, NgIf} from "@angular/common";
import {CustomDocument} from "../../../models/administrative-document/CustomDocument";
import {dateFormat} from "../../common/CommonMethod";
import {AttachedFile} from "../../../models/administrative-document/AttachedFile";

@Component({
  selector: 'app-document-list',
  standalone: true,
  imports: [
    FormsModule,
    ReactiveFormsModule,
    NgIf,
    DatePipe
  ],
  templateUrl: './document-list.component.html',
  styleUrl: './document-list.component.css'
})
export class DocumentListComponent implements OnInit {

  formGroup: FormGroup;
  nodeList: Node[] = [];
  private ROOT_NODE_ID: string = "00000000-0000-0000-0000-000000000000";

  constructor(private service: DocumentationService,
              private fb: FormBuilder) {
    this.formGroup = this.fb.group({
      documentName: ['', Validators.required],
      documentNumber: [''],
      date: [null, Validators.required],
      beginningDate: [null],
      expirationDate: [null],
      fileDescription: [''],
      tagSet: [[]],
    })
  }

  ngOnInit() {
    this.getParentNode(this.ROOT_NODE_ID);
  }

  document: CustomDocument | null = null;
  node: Node = {
    uuid: "",
    name: "",
    document: this.document,
  }
  parentNode: Node | null = null;

  getParentNode(id: string) {
    this.service.getNodeById(id).subscribe({
      next: value => {
        this.parentNode = value;
        this.getNodeList();
      },
      error: err => console.log(err)
    });
  }

  private getNodeList() {
    this.service.getNodeListByParentNodeId(this.parentNode!.uuid).subscribe({
      next: value => {
        this.nodeList = value;
      },
      error: err => console.log(err)
    });
  }

  entityIsCreated: boolean = true;

  onDocumentCreateClick() {
    this.entityIsCreated = true;
    this.formGroup.reset();
    this.node.parentNode = this.parentNode!;
  }

  onDocumentUpdateClick(document: CustomDocument) {
    this.entityIsCreated = false;
    this.document = document;
    this.formGroup.patchValue({
      documentName: this.document.docInfoGroup.name,
      documentNumber: this.document.docInfoGroup.number,
      date: dateFormat(this.document.date),
      beginningDate: dateFormat(this.document.beginningDate),
      expirationDate: dateFormat(this.document.expirationDate),
      fileDescription: this.document.attachedFile?.description,
      tagSet: this.document.tagSet,
    });
  }

  selectedFile: File | null = null;

  onFileSelected(event: any): void {
    let file: File = event.target.files[0];
    if (file && file.size > 0) {
      this.selectedFile = file;
    }
  }

  attachedFile: AttachedFile | null = null;

  uploadFile() {
    this.service.uploadFile(this.selectedFile!).subscribe({
      next: value => {
        this.attachedFile = value;
      },
      error: err => console.log(err)
    });
  }

  folderName: string = "";

  onFolderCreateClick() {
    this.folderName = "";
    this.node = {
      uuid: "",
      name: "",
      document: null,
      parentNode: this.parentNode!,
    }
  }

  createFolder() {
    this.node.name = this.folderName;
    this.service.createNode(this.node).subscribe({
      next: _ => {
        this.getNodeList();
      },
      error: err => console.log(err)
    });
  }

  get f() {
    return this.formGroup.controls;
  }

  saveDocument() {
    let document: CustomDocument = {
      uuid: this.entityIsCreated ? "" : this.document!.uuid,
      docInfoGroup: {
        name: this.f['documentName'].value,
        number: this.f['documentNumber'].value,
      },
      date: this.f['date'].value,
      beginningDate: this.f['beginningDate'].value,
      expirationDate: this.f['expirationDate'].value,
      tagSet: [], // TODO
      attachedFile: this.entityIsCreated ? this.attachedFile : this.document!.attachedFile,
    }
    if (this.entityIsCreated) {
      this.service.createCustomDocument(document).subscribe({
        next: value => {
          this.node.document = value;
          this.saveNode();
        },
        error: err => console.log(err)
      });
    } else {
      this.service.updateDocument(document).subscribe({
        next: _ => {
          this.getNodeList();
        },
        error: err => console.log(err)
      });
    }
  }

  saveNode() {
    this.service.createNode(this.node).subscribe({
      next: _ => {
        this.getNodeList();
      },
      error: err => console.log(err)
    });
  }

  onDocumentDeleteClick(node: Node) {
    this.node = node;
  }

  deleteNode() {
    this.service.deleteNodeById(this.node.uuid).subscribe({
      next: _ => {
        if (this.node.document) {
          this.deleteDocument();
        } else {
          this.getParentNode(this.parentNode!.parentNode!.uuid);
        }
      },
      error: err => console.log(err)
    });
  }

  deleteDocument() {
    this.service.deleteDocumentById(this.node.document!.uuid).subscribe({
      next: _ => {
        this.getNodeList();
      },
      error: err => console.log(err)
    });
  }

}
