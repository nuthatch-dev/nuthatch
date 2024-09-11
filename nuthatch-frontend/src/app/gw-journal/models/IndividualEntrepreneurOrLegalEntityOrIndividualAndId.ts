import {Individual} from "../../models/representative/Individual";
import {OrganizationWithOptionalSro} from "../../models/representative/OrganizationWithOptionalSro";

export interface IndividualEntrepreneurOrLegalEntityOrIndividualAndId {
  uuid: string;
  organizationWithOptionalSro: OrganizationWithOptionalSro | null;
  individual: Individual | null;
}
