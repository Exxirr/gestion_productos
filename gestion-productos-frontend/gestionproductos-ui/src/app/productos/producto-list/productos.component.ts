import { Component, OnInit } from '@angular/core';
import { Producto } from '../../model/producto';
import { ProductoService } from '../../service/producto-servicio.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-productos',
  templateUrl: './productos.component.html',
  styleUrl: './productos.component.css'
})
export class ProductosComponent implements OnInit {
  
  productos : Producto [] = [];

  constructor(private productoService : ProductoService, private router : Router){

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


  eliminarProducto(id : number){

    this.productoService.deleteByIdProduct(id).subscribe(

      () => this.listaProductos()

    );

   

  }


  actualizarProducto(id : number){
    this.router.navigate(['actualizar-productos', id]);
 }

}
