import {CustomDocument} from "./CustomDocument";

export interface Node {
  uuid: string,
  name: string,
  document: CustomDocument,
  parentNode?: Node,
}
