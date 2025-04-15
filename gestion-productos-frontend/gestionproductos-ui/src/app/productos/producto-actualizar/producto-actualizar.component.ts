import { ChangeDetectorRef, Component, OnInit } from '@angular/core';
import { ProductoService } from '../../service/producto-servicio.service';
import { Producto } from '../../model/producto';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-producto-actualizar',
  templateUrl: './producto-actualizar.component.html',
  styleUrls: ['./producto-actualizar.component.css']
})
export class ProductoActualizarComponent implements OnInit {

  id? : number;
  public producto: Producto = new Producto();

  constructor(
    private productoService: ProductoService, 
    private route : ActivatedRoute,
    private router : Router,
    private cdr : ChangeDetectorRef
  ){}


  ngOnInit(): void {

    this.id = Number(this.route.snapshot.paramMap.get('id'));

      this.productoService.ProductById(this.id).subscribe(
        data => {
          this.producto = data;
          this.cdr.detectChanges();
          console.log('Producto encontrado:', this.producto);
        },
        error => console.log(error)
      );
    }
  

  
    actualizarProducto() {
      if (this.id !== undefined) {
        this.producto.id = this.id; 
        this.productoService.updateProduct(this.id, this.producto).subscribe(
          data => {
            console.log('Producto actualizado:', data);
            this.router.navigate(['']);
          },
          error => console.error('Error al actualizar', error)
        );
      }
    }
}