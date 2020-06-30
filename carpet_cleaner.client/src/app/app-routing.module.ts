import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {AuthGuard} from "@providers/guards/auth.guard";

const routes: Routes = [
  {
    path: '',
    loadChildren: () => import('@pages/main-page/main-page.module')
      .then(module => module.MainPageModule),
    canActivate: [AuthGuard]
  },
  {
    path: 'login',
    loadChildren: () => import('@pages/login/login.module')
      .then(module => module.LoginModule)
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {}
