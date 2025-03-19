package com.proyecto.gestion.controlador;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.proyecto.gestion.dto.ProductoDTO;
import com.proyecto.gestion.entidad.Categoria;
import com.proyecto.gestion.entidad.Producto;

import com.proyecto.gestion.servicio.CategoriaServicio;
import com.proyecto.gestion.servicio.ProductoServicio;

import lombok.RequiredArgsConstructor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
@RequiredArgsConstructor // Agrega los constructores necesario para la inyeccion de depedencias
public class ProductoControlador {

	
	//Inyeccion de Dependencias
	private final ProductoServicio productoServicio;
	
	private final CategoriaServicio categoriaServicio;
	
	
	//Lista
	@GetMapping("producto")
	@ResponseStatus(HttpStatus.OK)
	public List<ProductoDTO> todosProductos(  ) {
		
		List<Producto> productos =  productoServicio.listarProductos();
		
		return productos.stream().map(producto -> ProductoDTO.builder()
				.id(producto.getId())
				.nombre_prod(producto.getNombre_prod())
				.precio_unitario(producto.getPrecio_unitario())
				.cantidad(producto.getCantidad())
				.idCategoria(producto.getCategoria() != null ? producto.getCategoria().getId() : null)
				.build())
				.collect(Collectors.toList());
			
	}
	
	//Listar Id
	@GetMapping("producto/{id}")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<?> todosProductosId(@PathVariable Integer id){
		
		Map<String, Object> response = new HashMap<>();
		
		if(!productoServicio.existePorId(id)) {
			
			response.put("mensaje", "El producto con ID " + id + " no se encuentra en la Base de Datos");	
			return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);		
		}
		
		Producto producto =  productoServicio.buscarProductoPorId(id);
		
		
		 
		ProductoDTO productoDto =  ProductoDTO.builder()
			.id(producto.getId() != null ? producto.getId() : null)
			.nombre_prod(producto.getNombre_prod())
			.precio_unitario(producto.getPrecio_unitario())
			.cantidad(producto.getCantidad())
			.idCategoria(producto.getCategoria().getId())
			.build();
		 
		  return  ResponseEntity.ok(productoDto);
		
	}
	
	
	//Agregar
	@PostMapping("producto")
	@ResponseStatus(HttpStatus.CREATED)
	public Producto nuevoProducto(@RequestBody ProductoDTO productoDto) {
		
		Producto productoSave = productoServicio.agregarProducto(productoDto);
		
		Categoria categoria = categoriaServicio.categoriaPorId(productoDto.getIdCategoria());
		
		return  Producto.builder()
		.id(productoSave.getId())
		.nombre_prod(productoSave.getNombre_prod())
		.precio_unitario(productoSave.getPrecio_unitario())
		.cantidad(productoSave.getCantidad())
		.categoria(categoria)
		.build();
	}
	
	
	
	
	//Actualizar 
	@PutMapping("producto/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public Producto actualizarProducto(@PathVariable Integer id, @RequestBody ProductoDTO productoActualizado) {
		
		Producto productoSave = productoServicio.actualizarProducto(productoActualizado);
		
		Categoria categoria = categoriaServicio.categoriaPorId(productoActualizado.getIdCategoria()); //null
		
		return  Producto.builder()
		.id(productoSave.getId())
		.nombre_prod(productoSave.getNombre_prod())
		.precio_unitario(productoSave.getPrecio_unitario())
		.cantidad(productoSave.getCantidad())
		.categoria(categoria)
		.build();
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
	
	
	
	
	
	
	
	
	
	
	
	
	
	
