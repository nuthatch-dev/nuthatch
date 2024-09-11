import {FullNameGroup} from "./FullNameGroup";
import {OrganizationWithOptionalSro} from "./OrganizationWithOptionalSro";

export interface Representative {
  uuid: string;
  fullNameGroup: FullNameGroup;
  organization: OrganizationWithOptionalSro;
  position: string;
  nostroyNumber: string;
  administrativeDocument: string;
}
