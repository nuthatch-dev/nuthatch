import {Individual} from "../../models/representative/Individual";
import {OrganizationWithOptionalSroAndId} from "../../models/representative/OrganizationWithOptionalSroAndId";

export interface IndividualEntrepreneurOrLegalEntityOrIndividualAndId {
  uuid: string;
  organizationWithOptionalSroAndId: OrganizationWithOptionalSroAndId | null;
  individual: Individual | null;
}
