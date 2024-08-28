import {Component, OnInit} from '@angular/core';
import {RepresentativeService} from "../representative.service";
import {FormBuilder, FormGroup, FormsModule, ReactiveFormsModule, Validators} from "@angular/forms";
import {IndividualEntrepreneur} from "../../models/IndividualEntrepreneur";
import {LegalEntity} from "../../models/LegalEntity";
import {Node} from "../../../administrative-documentation/models/Node";
import {Representative} from "../../models/Representative";
import {Router} from "@angular/router";
import {DatePipe, NgIf, NgOptimizedImage} from "@angular/common";

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

  private ROOT_NODE_ID: string = "00000000-0000-0000-0000-000000000000";

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
  private thumbnailsPrefix: string = "https://nuthatch-test.storage.yandexcloud.net/thumbnails/";

  previewDocument(node: Node) {
    this.selectedNode = node;
    if (this.selectedNode.document!.attachedFile) {
      this.pageCount = this.selectedNode.document!.attachedFile.thumbnails.length;
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

  onDocumentSelected() {
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
      administrativeDocument: this.f["administrativeDocument"].value,
    };
    this.service.createRepresentative(representative).subscribe({
      next: _ => {
        this.router.navigate(['/representative-list']);
      },
      error: err => console.log(err)
    });

  }

}
