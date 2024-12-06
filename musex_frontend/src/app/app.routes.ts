import { Routes } from '@angular/router';
import {HomePageComponent} from './components/home-page/home-page.component';
import {RegisterComponent} from './register/register.component';

export const routes: Routes = [
  {path: '', component: HomePageComponent},
  {path: 'register', component: RegisterComponent},
];
