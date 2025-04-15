import { Component, OnInit } from '@angular/core';
import { Producto } from '../../model/producto';
import { ProductoService } from '../../service/producto-servicio.service';

@Component({
  selector: 'app-producto-crear',
  templateUrl: './producto-crear.component.html',
  styleUrl: './producto-crear.component.css'
})
export class ProductoCrearComponent implements OnInit {

  id : number | undefined;
  nombre_prod : string = '';
  precio_unitario : number = 0;
  cantidad : number = 0;
  idCategoria : number = 0;


  constructor(private productoService : ProductoService){

  }


  ngOnInit(): void {


   
  }


  crearProducto(){
  
    let producto = new Producto(this.nombre_prod, this.precio_unitario, this.cantidad, this.idCategoria);
    
    console.log(producto);

    this.productoService.saveProduct(producto).subscribe(

      data => console.log(data)

    );
  }   


  





}
