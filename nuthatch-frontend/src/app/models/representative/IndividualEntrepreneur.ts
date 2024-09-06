import {FullNameGroup} from "./FullNameGroup";
import {Sro} from "./Sro";
import {CommonEntity} from "./CommonEntity";

export interface IndividualEntrepreneur extends CommonEntity {
  uuid: string;
  fullNameGroup: FullNameGroup;
  address: string;
  ogrnip: string;
  inn: string;
  sro: Sro;
}
