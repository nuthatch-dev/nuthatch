import {FullNameGroup} from "./FullNameGroup";
import {PassportDetails} from "./PassportDetails";
import {Role} from "./Role";

export interface Individual {
  uuid: string;
  fullNameGroup: FullNameGroup;
  address: string;
  isRussianFederationCitizen: boolean;
  passportDetails: PassportDetails;
  roleSet: Role[];
}
