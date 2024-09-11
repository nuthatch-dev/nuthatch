import {FullNameGroup} from "./FullNameGroup";
import {OrganizationWithOptionalSroAndId} from "./OrganizationWithOptionalSroAndId";

export interface Representative {
  uuid: string;
  fullNameGroup: FullNameGroup;
  organization: OrganizationWithOptionalSroAndId;
  position: string;
  nostroyNumber: string;
  administrativeDocument: string;
}
