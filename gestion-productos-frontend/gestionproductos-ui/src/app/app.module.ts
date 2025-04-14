import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { ProductosComponent } from './productos/producto-list/productos.component';
import { CategoriasComponent } from './categorias/categorias.component';
import { HttpClientModule } from '@angular/common/http';
import { ProductoCrearComponent } from './productos/producto-crear/producto-crear.component';
import { FormsModule } from '@angular/forms'; 

@NgModule({
  declarations: [
    ProductosComponent,
    CategoriasComponent,
    ProductoCrearComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule
  ],
  providers: [],
  bootstrap: [ProductoCrearComponent]
})
export class AppModule { }
