import {FullNameGroup} from "./FullNameGroup";
import {PassportDetails} from "./PassportDetails";
import {Role} from "./Role";

export interface Individual {
  uuid: string;
  fullNameGroup: FullNameGroup;
  address: string;
  isaRussianFederationCitizen: boolean;
  passportDetails: PassportDetails;
  roleSet: Role[];
}
