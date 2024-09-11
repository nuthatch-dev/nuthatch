import {CustomDocument} from "./CustomDocument";

export interface Node {
  uuid: string,
  name: string,
  document: CustomDocument | null,
  parentNode?: Node,
}
