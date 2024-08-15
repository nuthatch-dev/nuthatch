import {DocInfoGroup} from "./DocInfoGroup";
import {AttachedFile} from "./AttachedFile";

export interface CustomDocument {
  uuid: string,
  docInfoGroup: DocInfoGroup,
  date: Date,
  beginningDate: Date,
  expirationDate: Date,
  attachedFile?: AttachedFile,
  tagSet: string[],
}
