import { Routes } from '@angular/router';
import {LoginComponent} from "./features/auth/components/login/login.component";
import {ChatComponent} from "./features/chat/components/chat/chat.component";
import {AuthGuard} from "./guards/auth.guard";

export const routes: Routes = [
  {
    path: '',
    redirectTo: '/login',
    pathMatch: 'full'
  },
  {
    path: 'login',
    component: LoginComponent
  },
  {
    path: 'chat',
    component: ChatComponent,
    canActivate:[AuthGuard]
  }
];
