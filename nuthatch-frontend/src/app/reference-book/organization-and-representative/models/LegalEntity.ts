import {Sro} from "./Sro";
import {CommonEntity} from "../common/CommonEntity";

export interface LegalEntity extends CommonEntity {
  uuid: string;
  fullName: string;
  shortName: string;
  ogrn: string;
  inn: string;
  address: string;
  phone: string;
  sro: Sro;
}
