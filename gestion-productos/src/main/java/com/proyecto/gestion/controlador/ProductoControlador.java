package com.proyecto.gestion.controlador;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.proyecto.gestion.entidad.Producto;
import com.proyecto.gestion.servicio.ProductoServicio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping("/api/v1/")
public class ProductoControlador {


	@Autowired
	private ProductoServicio productoServicio;
	
	//Lista
	@GetMapping("producto")
	@ResponseStatus(HttpStatus.OK)
	public List<Producto> todosProductos(  ) {
		
		
		return productoServicio.listarProductos();
	}
	
	//Listar Id
	@GetMapping("producto/{id}")
	@ResponseStatus(HttpStatus.OK)
	public List<Producto> todosProductosId(@PathVariable Integer id){
		
		return (List<Producto>) productoServicio.buscarProductoPorId(id);
		
	}
	
	
	//Agregar
	@PostMapping("producto")
	@ResponseStatus(HttpStatus.CREATED)
	public Producto nuevoProducto(@RequestBody Producto producto) {
		
		return productoServicio.agregarProducto(producto);
		
	}
	
	
	
	
	//Actualizar
	@PutMapping("producto/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public Producto actualizarProducto(@PathVariable Integer id, @RequestBody Producto productoActualizado) {
		
		Producto productoExistente = productoServicio.buscarProductoPorId(id);
		
		if(productoExistente != null) {
			
			productoExistente.setId(id);
			productoExistente.setNombre_prod(productoActualizado.getNombre_prod());
			productoExistente.setPrecio_unitario(productoActualizado.getPrecio_unitario());
			productoExistente.setCantidad(productoActualizado.getCantidad());
			productoExistente.setCategoria(productoActualizado.getCategoria());
		}
		
		
		return productoServicio.actualizarProducto(productoExistente);
		
		
	}
	
	
	//Eliminar
	@DeleteMapping("producto/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void eliminarProducto(@PathVariable Integer id) {
		
		productoServicio.eliminarProducto(id);
		
	}
		

	
		
}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
