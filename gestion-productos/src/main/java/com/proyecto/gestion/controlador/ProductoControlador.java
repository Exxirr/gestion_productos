package com.proyecto.gestion.controlador;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.proyecto.gestion.exception.BadRequestException;
import com.proyecto.gestion.exception.ResourceNotFoundException;
import com.proyecto.gestion.model.dto.*;
import com.proyecto.gestion.model.entidad.Categoria;
import com.proyecto.gestion.model.entidad.Producto;
import com.proyecto.gestion.model.payload.ApiResponse;
import com.proyecto.gestion.model.payload.MensajeResponse;
import com.proyecto.gestion.servicio.CategoriaServicio;
import com.proyecto.gestion.servicio.ProductoServicio;

import lombok.RequiredArgsConstructor;
import java.util.List;
import java.util.stream.Collectors;


import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
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
	
	
	//Listar STREAM PRACTICAR
	@GetMapping("producto")
	@ResponseStatus(HttpStatus.OK)
	public List<ProductoDTO> todosProductos(  ) {
		
		List<Producto> productos =  productoServicio.listarProductos();
		
		
		if(productos == null || productos.isEmpty()) {
			
			throw new ResourceNotFoundException("producto");
			
		}
		

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

	public ResponseEntity<?> todosProductosId(@PathVariable Integer id){
		
		
		if(!productoServicio.existePorId(id)) {
			
			throw new ResourceNotFoundException("producto", "id", id);
			
			
		}
		
		Producto producto =  productoServicio.buscarProductoPorId(id);
		
		
		 
			ProductoDTO productoDto =  ProductoDTO.builder()
			.id(producto.getId() != null ? producto.getId() : null)
			.nombre_prod(producto.getNombre_prod())
			.precio_unitario(producto.getPrecio_unitario())
			.cantidad(producto.getCantidad())
			.idCategoria(producto.getCategoria().getId())
			.build();
		 
		  return new ResponseEntity<>(MensajeResponse.builder()
				  .mensaje("Consulta Exitosa")
				  .object(productoDto)
				  .build(), 
				  HttpStatus.OK);
		
	}
	
	
	//Agregar
		@PostMapping("producto")
	
		public ResponseEntity<?> nuevoProducto(@RequestBody ProductoDTO productoDto) {
			
					Producto productoSave = null;
					
					Categoria categoria = null;
			
			try {
				
				 categoria = categoriaServicio.categoriaPorId(productoDto.getIdCategoria()); 
				
				 productoSave = productoServicio.agregarProducto(productoDto);
				
				
					
					Producto producto =  Producto.builder()
							.id(productoSave.getId())
							.nombre_prod(productoSave.getNombre_prod())
							.precio_unitario(productoSave.getPrecio_unitario())
							.cantidad(productoSave.getCantidad())
							.categoria(categoria)
							.build();
							
							return new ResponseEntity<>(MensajeResponse.builder()
									.mensaje("Producto Agregado")
									.object(producto)
									.build(), 
					
									HttpStatus.CREATED);
				
	
			}catch(DataAccessException exDt) {
				
				return new ResponseEntity<>(new ApiResponse("Error al crear producto", "/producto"), HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}
	
	
	
	
	//Actualizar 
	@PutMapping("producto/{id}")

	public ResponseEntity<?> actualizarProducto(@PathVariable Integer id, @RequestBody ProductoDTO productoActualizado) {
		
		Producto productoUpdate = null;
		
		Categoria categoria = null;
		
		try {
				
			if(productoServicio.existePorId(id) || categoriaServicio.existePorId(productoActualizado.getIdCategoria())) {
				
				
				 categoria = categoriaServicio.categoriaPorId(productoActualizado.getIdCategoria()); 
					
				 productoUpdate = productoServicio.actualizarProducto(productoActualizado);
				
				
				Producto producto =  Producto.builder()
						.id(productoUpdate.getId())
						.nombre_prod(productoUpdate.getNombre_prod())
						.precio_unitario(productoUpdate.getPrecio_unitario())
						.cantidad(productoUpdate.getCantidad())
						.categoria(categoria)
						.build();
						
						return new ResponseEntity<>(MensajeResponse.builder()
								.mensaje("Producto Actualizado correctamente")
								.object(producto)
								.build(), 
				
								HttpStatus.CREATED);
			}
			
			return new ResponseEntity<>(MensajeResponse.builder()
					.mensaje("El producto o categoria no existe")
					.object(null)
					.build(), 
					HttpStatus.NOT_FOUND);		
			
			
			
		}catch(DataAccessException exDt) {
			
			return new ResponseEntity<>(new ApiResponse("Eror al actualizar Producto", "/producto{id}"), HttpStatus.INTERNAL_SERVER_ERROR);	
		}
	}
	
	
	//Eliminar
	@DeleteMapping("producto/{id}")
	public ResponseEntity<?> eliminarProducto(@PathVariable Integer id) {
		
		try {
				
		if(!productoServicio.existePorId(id)) {
				
			return new ResponseEntity<>(MensajeResponse.builder()
										.mensaje("El producto a eliminar no existe")
										.object(null)
										.build(), 
										HttpStatus.NOT_FOUND);		
		}
		
		//Elimina por Id
		
		productoServicio.eliminarProducto(id);
		
		return new ResponseEntity<>(MensajeResponse.builder()
				.mensaje("Producto Eliminado")
				.object(null)
				.build(), 
				HttpStatus.OK);	
		
		
		}catch(DataAccessException exDt) {
			
			
			return new ResponseEntity<>(new ApiResponse("Eror al eliminar Empleado", "/producto{id}"), HttpStatus.INTERNAL_SERVER_ERROR);	
		}	
	}
}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
