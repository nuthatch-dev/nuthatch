import {FullNameGroup} from "./FullNameGroup";
import {PassportDetails} from "./PassportDetails";
import {CommonEntity} from "./CommonEntity";

export interface Individual extends CommonEntity {
  uuid: string;
  fullNameGroup: FullNameGroup;
  address: string;
  isaRussianFederationCitizen: boolean;
  passportDetails: PassportDetails;
}
