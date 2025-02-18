package com.proyecto.gestion.controlador;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.proyecto.gestion.dto.ProductoDTO;
import com.proyecto.gestion.entidad.Producto;
import com.proyecto.gestion.repositorio.ProductoRepositorio;
import com.proyecto.gestion.servicio.ProductoServicio;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
	public ProductoDTO todosProductosId(@PathVariable Integer id){
		
		return (List<Producto>) productoServicio.buscarProductoPorId(id);
		
	}
	
	
	//Agregar
	@PostMapping("producto")
	@ResponseStatus(HttpStatus.CREATED)
	public Producto nuevoProducto(@RequestBody ProductoDTO producto) {
		
		return productoServicio.agregarProducto(producto);
		
	}
	
	
	
	
	//Actualizar
	@PutMapping("producto/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public Producto actualizarProducto(@PathVariable Integer id, @RequestBody ProductoDTO productoActualizado) {
		
		ProductoDTO productoExistente = productoServicio.buscarProductoPorId(id);
		
		if(productoExistente != null) {
			
			productoExistente.setId(id);
			productoExistente.setNombre_prod(productoActualizado.getNombre_prod());
			productoExistente.setPrecio_unitario(productoActualizado.getPrecio_unitario());
			productoExistente.setCantidad(productoActualizado.getCantidad());
			productoExistente.setIdCategoria(id);
		}
		
		
		return productoServicio.actualizarProducto(productoExistente);
		
		
	}
	
	
	//Eliminar
	@DeleteMapping("producto/{id}")
	public ResponseEntity<?> eliminarProducto(@PathVariable Integer id) {
		
		Map<String, Object> response = new HashMap<>();
		
		try {
				
		if(!productoServicio.existePorId(id)) {
			
			response.put("mensaje", "El producto con ID " + id + " no existe.");	
			return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);		
		}
		
		//Elimina por Id
		productoServicio.eliminarProducto(id);
		
		response.put("mensaje", "Producto eliminado");
		return new ResponseEntity<>(response, HttpStatus.OK);
		
		
		}catch(DataAccessException exDt) {
			
			response.put("mensaje", exDt.getMessage());
			
			response.put("producto", null);
			
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}	
	}
}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
