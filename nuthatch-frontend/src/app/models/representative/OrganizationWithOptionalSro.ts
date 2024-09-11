import {LegalEntity} from "./LegalEntity";
import {IndividualEntrepreneur} from "./IndividualEntrepreneur";

export interface OrganizationWithOptionalSro {
  uuid: string;
  legalEntity: LegalEntity | null;
  individualEntrepreneur: IndividualEntrepreneur | null;
}
