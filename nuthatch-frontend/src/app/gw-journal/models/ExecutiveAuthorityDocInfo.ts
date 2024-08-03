import {DocInfo} from "./DocInfo";

export interface ExecutiveAuthorityDocInfo {
  uuid: string;
  docInfo: DocInfo;
  expirationDate: Date;
  docChangeDate: Date;
  executiveAuthorityId: string;
  executiveAuthorityTitle: string;
}
