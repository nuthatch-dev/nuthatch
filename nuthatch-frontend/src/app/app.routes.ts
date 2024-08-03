import {Routes} from '@angular/router';
import {HeaderComponent} from "./header/header.component";
import {FooterComponent} from "./footer/footer.component";
import {GwjListComponent} from "./gw-journal/gwj-list/gwj-list.component";
import {GwjDetailsComponent} from "./gw-journal/gwj-details/gwj-details.component";
import {GwjCreateComponent} from "./gw-journal/gwj-create/gwj-create.component";

export const routes: Routes = [
  {path: '', outlet: 'header', component: HeaderComponent},
  {path: '', outlet: 'footer', component: FooterComponent},

  {path: 'gwj-list', component: GwjListComponent},
  {path: 'gwj-details/:id', component: GwjDetailsComponent},
  {path: 'gwj-create', component: GwjCreateComponent},
];
