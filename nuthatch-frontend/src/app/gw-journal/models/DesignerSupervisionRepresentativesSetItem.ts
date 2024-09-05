import {OrganizationWithOptionalSro} from "./OrganizationWithOptionalSro";
import {ProjectDocSection} from "./ProjectDocSection";

export interface DesignerSupervisionRepresentativesSetItem {
  uuid: string;
  representativesIdsSet: string[];
  organization: OrganizationWithOptionalSro;
  projectDocSectionsSet: ProjectDocSection[];
}
