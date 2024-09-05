import {OrganizationWithOptionalSro} from "./OrganizationWithOptionalSro";

export interface OrganizationWithOptionalSroAndId {
  uuid: string;
  organizationInfo: OrganizationWithOptionalSro;
}
