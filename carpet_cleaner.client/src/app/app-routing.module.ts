import { ExtraOptions, RouterModule, Routes } from '@angular/router';
import { NgModule } from '@angular/core';

export const routes: Routes = [
  {
    path: '',
    loadChildren: () => import ('./main/main.module')
      .then(m => m.MainModule)
  },
  {
    path: 'pages',
    loadChildren: () => import('./example_pages/pages.module')
      .then(m => m.PagesModule),
  },
  { path: '**', redirectTo: 'pages' },
];

const config: ExtraOptions = {
  useHash: false,
};

@NgModule({
  imports: [RouterModule.forRoot(routes, config)],
  exports: [RouterModule],
})
export class AppRoutingModule {
}
