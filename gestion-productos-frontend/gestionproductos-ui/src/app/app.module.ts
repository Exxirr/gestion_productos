import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { ProductosComponent } from './productos/producto-list/productos.component';
import { CategoriasComponent } from './categorias/categorias.component';
import { HttpClientModule } from '@angular/common/http';
import { ProductoCrearComponent } from './productos/producto-crear/producto-crear.component';
import { FormsModule } from '@angular/forms'; 
import { DefaultComponent } from './default/default.component';
import { ProductoActualizarComponent } from './productos/producto-actualizar/producto-actualizar.component';

@NgModule({
  declarations: [
    ProductosComponent,
    CategoriasComponent,
    ProductoCrearComponent,
    DefaultComponent,
    ProductoActualizarComponent,
   
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule
  ],
  providers: [],
  bootstrap: [DefaultComponent]
})
export class AppModule { }
