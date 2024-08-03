import {Routes} from '@angular/router';
import {HeaderComponent} from "./header/header.component";
import {FooterComponent} from "./footer/footer.component";

export const routes: Routes = [
  {path: '', outlet: 'header', component: HeaderComponent},
  {path: '', outlet: 'footer', component: FooterComponent},
];
