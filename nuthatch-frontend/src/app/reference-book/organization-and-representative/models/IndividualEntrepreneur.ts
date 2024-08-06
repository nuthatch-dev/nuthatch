import {FullNameGroup} from "./FullNameGroup";
import {Sro} from "./Sro";
import {Role} from "./Role";

export interface IndividualEntrepreneur {
  uuid: string;
  fullNameGroup: FullNameGroup;
  address: string;
  ogrnip: string;
  inn: string;
  sro: Sro;
  roleSet: Role[];
}
