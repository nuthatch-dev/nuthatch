import {OrganizationWithOptionalSro} from "./OrganizationWithOptionalSro";
import {Individual} from "../../models/representative/Individual";

export interface IndividualEntrepreneurOrLegalEntityOrIndividualAndId {
  uuid: string;
  organizationWithOptionalSro: OrganizationWithOptionalSro | null;
  individual: Individual | null;
}
