import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Producto } from '../model/producto';

@Injectable({
  providedIn: 'root'
})
export class ProductoService {

  private api : string = 'http://localhost:8080/api/v1/producto'


  constructor(private http: HttpClient) { }

  //Listar Productos
   getProductosList():Observable<Producto []> {

      return this.http.get<Producto []>(this.api);
   }

   saveProduct(producto : Producto):Observable<Producto> {

      return this.http.post<Producto>(this.api, producto);

   }

   deleteByIdProduct(id : number):Observable<any>{

      return this.http.delete(this.api+'/'+id);
   }



   updateProduct(id : number, producto: Producto): Observable<Object> {
      return this.http.put(`${this.api}/${id}`, producto);
  }
  

   
   ProductById(id : number): Observable<Producto>{
      return this.http.get<Producto>(`${this.api}/${id}`);
   }

   
}
