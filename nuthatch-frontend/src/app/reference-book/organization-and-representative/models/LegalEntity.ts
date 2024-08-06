import {Sro} from "./Sro";
import {Role} from "./Role";

export interface LegalEntity {
  uuid: string;
  fullName: string;
  shortName: string;
  ogrn: string;
  inn: string;
  address: string;
  phone: string;
  sro: Sro;
  roleSet: Role[];
}
