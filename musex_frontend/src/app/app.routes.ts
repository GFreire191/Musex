import { Routes } from '@angular/router';
import {HomePageComponent} from './components/home-page/home-page.component';
import {RegisterComponent} from './register/register.component';
import {LoginComponent} from './login/login.component';
import {ProfileComponent} from './profile/profile.component';

export const routes: Routes = [
  {path: '', component: HomePageComponent},
  {path: 'register', component: RegisterComponent},
  {path: 'login', component: LoginComponent},
  {path: 'profile/:id', component: ProfileComponent}
];
