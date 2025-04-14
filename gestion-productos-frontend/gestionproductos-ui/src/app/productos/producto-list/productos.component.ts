import { Component, OnInit } from '@angular/core';
import { Producto } from '../../model/producto';
import { ProductoService } from '../../service/producto-servicio.service';

@Component({
  selector: 'app-productos',
  templateUrl: './productos.component.html',
  styleUrl: './productos.component.css'
})
export class ProductosComponent implements OnInit {
  
  productos : Producto [] = [];

  constructor(private productoService : ProductoService){

  }

  ngOnInit(): void {

    this.listaProductos();

  }

  listaProductos() {

    this.productoService.getProductosList()
    .subscribe(
      
       data => {
        this.productos = data;
        console.log(this.productos);
       }
    );
  }


    

}
