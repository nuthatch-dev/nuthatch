import {LegalEntity} from "../../models/representative/LegalEntity";
import {IndividualEntrepreneur} from "../../models/representative/IndividualEntrepreneur";

export interface OrganizationWithOptionalSro {
  legalEntity: LegalEntity | null;
  individualEntrepreneur: IndividualEntrepreneur | null;
}
