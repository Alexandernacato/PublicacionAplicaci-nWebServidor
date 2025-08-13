import { Routes } from '@angular/router';
import { Catalogo } from './catalogo/catalogo';
import { CatologoC } from './catologo-c/catologo-c';
export const routes: Routes = [
   { path: '', component: Catalogo },
   {path: 'catologo', component: Catalogo },
   {path: 'catologo-c', component: CatologoC },

  
];
