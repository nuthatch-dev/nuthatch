import {LegalEntity} from "./LegalEntity";
import {IndividualEntrepreneur} from "./IndividualEntrepreneur";

export interface OrganizationWithOptionalSro {
  legalEntity: LegalEntity | null;
  individualEntrepreneur: IndividualEntrepreneur | null;
}
