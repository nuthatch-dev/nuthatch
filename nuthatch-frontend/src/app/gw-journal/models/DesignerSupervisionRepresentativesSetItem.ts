import {ProjectDocSection} from "./ProjectDocSection";
import {OrganizationWithOptionalSro} from "../../models/representative/OrganizationWithOptionalSro";

export interface DesignerSupervisionRepresentativesSetItem {
  uuid: string;
  representativesIdsSet: string[];
  organization: OrganizationWithOptionalSro;
  projectDocSectionsSet: ProjectDocSection[];
}
