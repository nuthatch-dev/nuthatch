import {Routes} from '@angular/router';
import {HeaderComponent} from "./header/header.component";
import {FooterComponent} from "./footer/footer.component";
import {GwjListComponent} from "./gw-journal/gwj-list/gwj-list.component";
import {GwjDetailsComponent} from "./gw-journal/gwj-details/gwj-details.component";
import {GwjCreateComponent} from "./gw-journal/gwj-create/gwj-create.component";
import {
  IndividualListComponent
} from "./reference-book/organization-and-representative/individual/individual-list/individual-list.component";
import {
  IndividualEntrepreneurListComponent
} from "./reference-book/organization-and-representative/individual-entrepreneur/individual-entrepreneur-list/individual-entrepreneur-list.component";
import {
  LegalEntityListComponent
} from "./reference-book/organization-and-representative/legal-entity/legal-entity-list/legal-entity-list.component";
import {SroListComponent} from "./reference-book/organization-and-representative/sro/sro-list/sro-list.component";
import {RoleListComponent} from "./reference-book/organization-and-representative/role/role-list/role-list.component";
import {
  RepresentativeListComponent
} from "./reference-book/organization-and-representative/representative/representative-list/representative-list.component";

export const routes: Routes = [
  {path: '', outlet: 'header', component: HeaderComponent},
  {path: '', outlet: 'footer', component: FooterComponent},

  {path: 'gwj-list', component: GwjListComponent},
  {path: 'gwj-details/:id', component: GwjDetailsComponent},
  {path: 'gwj-create', component: GwjCreateComponent},

  // Organization and representative routes
  {path: 'individual-list', component: IndividualListComponent},
  {path: 'individual-entrepreneur-list', component: IndividualEntrepreneurListComponent},
  {path: 'legal-entity-list', component: LegalEntityListComponent},
  {path: 'sro-list', component: SroListComponent},
  {path: 'representative-list', component: RepresentativeListComponent},
  {path: 'role-list', component: RoleListComponent},

];
