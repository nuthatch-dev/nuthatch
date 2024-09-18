import {Directive, EventEmitter, Input, OnChanges, Output, SimpleChanges, TemplateRef, ViewChild} from "@angular/core";
import {Representative} from "../../../../../models/representative/Representative";
import {OrganizationWithOptionalSro} from "../../../../../models/representative/OrganizationWithOptionalSro";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {DeveloperRepresentativeService} from "../../../developer-representative/developer-representative.service";
import {CustomDocument} from "../../../../../models/administrative-document/CustomDocument";

@Directive()
export abstract class CommonSelect implements OnChanges {

  availableRepresentativeList: Representative[] = [];
  selectedRepresentativeList: Representative[] = [];

  @ViewChild("selectFromListTemplate", {static: false}) selectFromListTemplate!: TemplateRef<any>;
  @ViewChild("createRepresentativeTemplate", {static: false}) createRepresentativeTemplate!: TemplateRef<any>;
  selectedTemplate: TemplateRef<any> | null = null;

  @Input() counterparty: OrganizationWithOptionalSro | null = null;
  @Input() counterpartyId: string = "";

  @Output() onRepresentativeSelected = new EventEmitter<string[]>();

  private representativeSelected() {
    this.onRepresentativeSelected.emit(this.selectedRepresentativeList.map(r => r.uuid));
  }

  createRepresentativeFormGroup: FormGroup;

  constructor(private service: DeveloperRepresentativeService,
              private fb: FormBuilder) {
    this.createRepresentativeFormGroup = this.fb.group({
      lastName: ["", Validators.required],
      firstName: ["", Validators.required],
      middleName: [""],
      position: ["", Validators.required],
      nostroyNumber: [""],
      documentName: ["", Validators.required],
      documentDate: [null, Validators.required],
      documentNumber: ["", Validators.required],
    });
  }

  ngOnChanges(changes: SimpleChanges): void {
    let counterpartyId: string = changes["counterpartyId"].currentValue;
    this.service.getRepresentativeListByCounterpartyId(counterpartyId).subscribe({
      next: value => {
        if (value.length == 0) {
          this.selectedTemplate = this.createRepresentativeTemplate;
        } else {
          this.availableRepresentativeList = value;
          this.selectedTemplate = this.selectFromListTemplate;
        }
      },
      error: err => console.log(err)
    });
  }

  addToList(r: Representative) {
    this.availableRepresentativeList.splice(this.availableRepresentativeList.indexOf(r), 1);
    this.selectedRepresentativeList.push(r);
    this.representativeSelected();
  }

  removeFromList(r: Representative) {
    this.selectedRepresentativeList.splice(this.selectedRepresentativeList.indexOf(r), 1);
    this.availableRepresentativeList.push(r);
    this.representativeSelected();
  }

  onCreateRepresentativeClick() {
    this.selectedTemplate = this.createRepresentativeTemplate;
  }

  get f() {
    return this.createRepresentativeFormGroup.controls;
  }

  createRepresentative() {
    let document: CustomDocument = {
      uuid: "",
      docInfoGroup: {
        name: this.f["documentName"].value,
        number: this.f["documentNumber"].value,
      },
      date: this.f["documentDate"].value,
      beginningDate: null,
      expirationDate: null,
      attachedFile: null,
      tagSet: [],
    }
    this.service.createCustomDocument(document).subscribe({
      next: cd => {
        let representative: Representative = {
          uuid: "",
          fullNameGroup: {
            lastName: this.f["lastName"].value,
            firstName: this.f["firstName"].value,
            middleName: this.f["middleName"].value,
          },
          organization: this.counterparty!,
          position: this.f["position"].value,
          nostroyNumber: this.f["nostroyNumber"].value,
          administrativeDocument: cd.uuid,
        }
        this.service.createRepresentative(representative).subscribe({
          next: r => {
            this.addToList(r);
          },
          error: err => console.log(err)
        });
      },
      error: err => console.log(err)
    });
  }

}
