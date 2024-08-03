import {OrganizationWithOptionalSroAndId} from "./OrganizationWithOptionalSroAndId";
import {ProjectDocSection} from "./ProjectDocSection";

export interface DesignerSupervisionRepresentativesSetItem {
  uuid: string;
  representativesIdsSet: string[];
  organization: OrganizationWithOptionalSroAndId;
  projectDocSectionsSet: ProjectDocSection[];
}
