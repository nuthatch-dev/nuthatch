import {Component, OnInit} from '@angular/core';
import {RepresentativeService} from "../representative.service";
import {FormBuilder, FormGroup, FormsModule, ReactiveFormsModule, Validators} from "@angular/forms";
import {IndividualEntrepreneur} from "../../../../models/representative/IndividualEntrepreneur";
import {LegalEntity} from "../../../../models/representative/LegalEntity";
import {Node} from "../../../administrative-documentation/models/Node";
import {Representative} from "../../../../models/representative/Representative";
import {Router} from "@angular/router";
import {DatePipe, NgIf, NgOptimizedImage} from "@angular/common";
import {environment} from "../../../../environment";

@Component({
  selector: 'app-representative-create',
  standalone: true,
  imports: [
    FormsModule,
    NgIf,
    ReactiveFormsModule,
    DatePipe,
    NgOptimizedImage
  ],
  templateUrl: './representative-create.component.html',
  styleUrl: './representative-create.component.css'
})
export class RepresentativeCreateComponent implements OnInit {

  private ROOT_NODE_ID: string = environment.ROOT_NODE_ID;

  formGroup: FormGroup;
  individualEntrepreneurList: IndividualEntrepreneur[] = [];
  legalEntityList: LegalEntity[] = [];

  constructor(private service: RepresentativeService,
              private fb: FormBuilder,
              private router: Router) {
    this.formGroup = this.fb.group({
      lastName: ["", Validators.required],
      firstName: ["", Validators.required],
      middleName: [""],
      isLegalEntityRepresentative: [true],
      legalEntity: [null],
      individualEntrepreneur: [null],
      position: ["", Validators.required],
      nostroyNumber: [""],
      administrativeDocument: ["", Validators.required],
    });
  }

  ngOnInit() {
    this.getIndividualEntrepreneurList();
    this.getLegalEntityList();
    this.getParentNode(this.ROOT_NODE_ID);
  }

  /*
  Получить списки ИП и ЮЛ
   */
  private getIndividualEntrepreneurList() {
    this.service.getIndividualEntrepreneurList().subscribe({
      next: value => {
        this.individualEntrepreneurList = value;
      },
      error: err => console.log(err)
    });
  }

  private getLegalEntityList() {
    this.service.getLegalEntityList().subscribe({
      next: value => {
        this.legalEntityList = value;
      },
      error: err => console.log(err)
    });
  }

  /*
  Список документов
   */
  parentNode: Node | null = null;
  nodeList: Node[] = [];

  getParentNode(id: string) {
    this.service.getNodeById(id).subscribe({
      next: value => {
        this.parentNode = value;
        this.selectedNode = null;
        this.selectedThumbnail = null;

        this.pageCount = 0;
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

  selectedNode: Node | null = null;

  selectedThumbnail: string | null = null;
  private thumbnailsPrefix: string = environment.S3_URL + "/thumbnails/";

  previewDocument(node: Node) {
    this.currentPage = 0;
    this.pageCount = 0;
    this.selectedNode = node;
    if (this.selectedNode.document && this.selectedNode.document.attachedFile) {
      this.pageCount = this.selectedNode.document.attachedFile.thumbnails.length;
      this.showThumbnail();
    } else {
      this.selectedThumbnail = null;
    }
  }

  currentPage: number = 0;
  pageCount: number = 0;
  showThumbnail() {
    let filename: string = this.selectedNode!.document!.attachedFile!.thumbnails[this.currentPage];
    this.selectedThumbnail = this.thumbnailsPrefix + this.selectedNode!.document!.attachedFile?.name + "/" + filename;
  }
  getPreviousPage() {
    if (this.currentPage > 0) {
      this.currentPage--;
      this.showThumbnail();
    }
  }
  getNextPage() {
    if (this.currentPage < this.pageCount - 1) {
      this.currentPage++;
      this.showThumbnail();
    }
  }

  administrativeDocumentId: string = "";

  onDocumentSelected() {
    this.administrativeDocumentId = this.selectedNode!.document!.uuid;
    this.formGroup.patchValue({
      administrativeDocument: this.selectedNode!.document!.docInfoGroup.name + " "
        + this.selectedNode!.document!.docInfoGroup.number + " "
        + this.selectedNode!.document!.date,
    });
  }

  get f() {
    return this.formGroup.controls;
  }

  saveRepresentative() {
    /*
    Удаляем остатки информации об организации при смене ЮЛ->ИП / ИП->ЮЛ
     */
    if (this.f['isLegalEntityRepresentative'].value) {
      this.formGroup.patchValue({
        individualEntrepreneur: null,
      });
    } else {
      this.formGroup.patchValue({
        legalEntity: null,
      });
    }

    let representative: Representative = {
      uuid: "",
      fullNameGroup: {
        lastName: this.f["lastName"].value,
        firstName: this.f["firstName"].value,
        middleName: this.f["middleName"].value,
      },
      legalEntity: this.f["legalEntity"].value,
      individualEntrepreneur: this.f["individualEntrepreneur"].value,
      position: this.f["position"].value,
      nostroyNumber: this.f["nostroyNumber"].value,
      administrativeDocument: this.administrativeDocumentId,
    };
    this.service.createRepresentative(representative).subscribe({
      next: _ => {
        this.router.navigate(['/representative-list']);
      },
      error: err => console.log(err)
    });

  }

}
