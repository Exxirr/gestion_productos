export class Producto {

    public id?: number;
    public nombre_prod?: string;
    public precio_unitario?: number;
    public cantidad?: number;
    public idCategoria?: number;

    constructor(nombre_prod?: string, precio_unitario?: number, cantidad?: number, idCategoria?: number, id?: number) {
        this.nombre_prod = nombre_prod;
        this.precio_unitario = precio_unitario;
        this.cantidad = cantidad;
        this.idCategoria = idCategoria;
        this.id = id;  // `id` puede ser opcional, pero lo puedes establecer cuando actualizas un producto.


    }

}
