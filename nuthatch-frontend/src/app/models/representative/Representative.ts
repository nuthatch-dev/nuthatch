import {FullNameGroup} from "./FullNameGroup";
import {LegalEntity} from "./LegalEntity";
import {IndividualEntrepreneur} from "./IndividualEntrepreneur";

export interface Representative {
  uuid: string;
  fullNameGroup: FullNameGroup;
  legalEntity: LegalEntity;
  individualEntrepreneur: IndividualEntrepreneur;
  position: string;
  nostroyNumber: string;
  administrativeDocument: string;
}
