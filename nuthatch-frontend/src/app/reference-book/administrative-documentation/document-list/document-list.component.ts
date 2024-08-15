import {Component, OnInit} from '@angular/core';
import {DocumentationService} from "../documentation.service";
import {Node} from "../models/Node";
import {FormBuilder, FormGroup, FormsModule, ReactiveFormsModule, Validators} from "@angular/forms";
import {DatePipe, NgIf} from "@angular/common";
import {CustomDocument} from "../models/CustomDocument";

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
    this.getNodeList();
  }

  private getNodeList() {
    this.service.getNodeList().subscribe({
      next: value => {
        this.nodeList = value;
      },
      error: err => console.log(err)
    });
  }

  entityIsCreated: boolean = true;

  onCreateClick(node: Node) {
    this.entityIsCreated = true;
    this.formGroup.reset();
    this.node.parentNode = node;
  }

  onUpdateClick() {
  }

  onFileSelected(event: any): void {
    let file: File = event.target.files[0];
    if (file && file.size > 0) {

    }
  }

  get f() {
    return this.formGroup.controls;
  }

  saveDocument() {
    let document: CustomDocument = {
      uuid: this.entityIsCreated ? "" : this.document.uuid,
      docInfoGroup: {
        name: this.f['documentName'].value,
        number: this.f['documentNumber'].value,
      },
      date: this.f['date'].value,
      beginningDate: this.f['beginningDate'].value,
      expirationDate: this.f['expirationDate'].value,
      tagSet: [], // TODO
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
// TODO
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

  document: CustomDocument = {
    uuid: "",
    docInfoGroup: {
      name: "",
      number: "",
    },
    date: new Date(),
    beginningDate: new Date(),
    expirationDate: new Date(),
    attachedFile: {
      uuid: "",
      name: "",
      description: "",
      checksum: "",
    },
    tagSet: [],
  }

  node: Node = {
    uuid: "",
    name: "",
    document: this.document,
  }

}
