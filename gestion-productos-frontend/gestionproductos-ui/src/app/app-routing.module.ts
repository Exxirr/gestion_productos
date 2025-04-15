import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ProductosComponent } from './productos/producto-list/productos.component';
import { ProductoCrearComponent } from './productos/producto-crear/producto-crear.component';
import { ProductoActualizarComponent } from './productos/producto-actualizar/producto-actualizar.component';


const routes: Routes = [
  {path: '', component : ProductosComponent}, //http://localhost:4200
  {path: 'productos/crear', component : ProductoCrearComponent},
  {path: 'actualizar-productos/:id', component: ProductoActualizarComponent,}

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
