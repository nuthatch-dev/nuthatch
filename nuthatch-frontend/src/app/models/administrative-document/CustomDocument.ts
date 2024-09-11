import {DocInfoGroup} from "./DocInfoGroup";
import {AttachedFile} from "./AttachedFile";

export interface CustomDocument {
  uuid: string,
  docInfoGroup: DocInfoGroup,
  date: Date,
  beginningDate: Date | null,
  expirationDate: Date | null,
  attachedFile: AttachedFile | null,
  tagSet: string[],
}
