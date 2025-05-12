package com.proyecto.gestion.controlador;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.proyecto.gestion.model.dto.CategoriaDTO;
import com.proyecto.gestion.model.entidad.Categoria;
import com.proyecto.gestion.model.payload.ApiResponse;
import com.proyecto.gestion.model.payload.MensajeResponse;
import com.proyecto.gestion.servicio.CategoriaServicio;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;


@RestController
@RequestMapping("/api/v1/")
@RequiredArgsConstructor
public class CategoriaControlador {

	private final CategoriaServicio categoriaServicio;
	
	
	@GetMapping("categoria")
	public ResponseEntity<List<CategoriaDTO>> todasCategoria() {
		
		
	    List<CategoriaDTO> categoriasLista = categoriaServicio.listarCategorias().stream()
	        .map(categoria -> CategoriaDTO.builder()
	            .id(categoria.getId())
	            .nombre_cat(categoria.getNombre_cat())
	            .build())
	        .collect(Collectors.toList());

	    return ResponseEntity.ok(categoriasLista);
	}
	
	
	
	@GetMapping("categoria/{id}")
	public ResponseEntity<?> todosProductosId(@PathVariable Integer id){
		
			if(!categoriaServicio.existePorId(id)) {
				
				return new ResponseEntity<>(MensajeResponse.builder()
						.mensaje("La categoria con ID " + id + " no se encuentra en la Base de Datos")
						.object(null)
						.build(),
						HttpStatus.NOT_FOUND);		
			}
		
		Categoria categoria = categoriaServicio.categoriaPorId(id);
		
		CategoriaDTO categoriaDTO = CategoriaDTO.builder()
				.id(categoria.getId() != null ? categoria.getId() : null)
				.nombre_cat(categoria.getNombre_cat())
				.build();
		
		
		 return new ResponseEntity<>(MensajeResponse.builder()
				  .mensaje("Consulta Exitosa")
				  .object(categoriaDTO)
				  .build(), 
				  HttpStatus.OK);
			
		
	}
	
	
	

	
	@PostMapping("categoria")
	public ResponseEntity<?> agregarCategoria( @RequestBody @Valid CategoriaDTO categoriaDto) {
		
		
		Categoria categoria = null;
		
		
		try {
			
			categoria = categoriaServicio.agregarCategoria(categoriaDto);
			
			Categoria nuevaCat = Categoria.builder()
					.id(categoria.getId())
					.nombre_cat(categoria.getNombre_cat())
					.build();
			
			return new ResponseEntity<>(MensajeResponse.builder()
					.mensaje("Categoria creada")
					.object(nuevaCat)
					.build(), 
					HttpStatus.CREATED);
			
			
		}catch(DataAccessException exDt) {
		
			return new ResponseEntity<>(new ApiResponse("Eror al crear Categoria", "/categoria"), HttpStatus.INTERNAL_SERVER_ERROR);	
			
		}	
	}
	
	
	@PutMapping("categoria/{id}")
	public ResponseEntity<?> actualizarCategoria(@PathVariable Integer id,  @RequestBody CategoriaDTO categoriaDto) {
		
		
		Categoria categoriaUpdate = null;
		
		
		try {
		
				if(categoriaServicio.existePorId(id)) {
					
					categoriaDto.setId(id);
					categoriaUpdate = categoriaServicio.actualizarCategoria(categoriaDto);
					
					
					Categoria categoria = Categoria.builder()
							.id(categoriaUpdate.getId())
							.nombre_cat(categoriaUpdate.getNombre_cat())
							.build();
					
					return new ResponseEntity<>(MensajeResponse.builder()
							.mensaje("Categoria actualizada correctamente")
							.object(categoria)
							.build(), 
			
							HttpStatus.CREATED);
				}
			
				return new ResponseEntity<>(MensajeResponse.builder()
						.mensaje("La categoria no existe")
						.object(null)
						.build(), 
						HttpStatus.NOT_FOUND);	
			
			
		}catch(DataAccessException exDt) {
		
			return new ResponseEntity<>(new ApiResponse("Eror al actualizar Categoria", "/categoria{id}"), HttpStatus.INTERNAL_SERVER_ERROR);	
			
		}	
	}
	
	
	@DeleteMapping("categoria/{id}")
	public ResponseEntity<?> eliminarCategoria(@PathVariable Integer id) {
		
		try {
			
			if(categoriaServicio.existePorId(id)) {
				
				categoriaServicio.eliminarCategoria(id);
				
				return new ResponseEntity<>(MensajeResponse.builder()
						.mensaje("Categoria eliminada")
						.object(null)
						.build(), 
						HttpStatus.OK);
				
			}
			
			return new ResponseEntity<>(MensajeResponse.builder()
					.mensaje("Categoria no existe")
					.object(null)
					.build(), 
					HttpStatus.NOT_FOUND);
			
			
		}catch(DataAccessException exDt) {
			
			
			return new ResponseEntity<>(new ApiResponse("Eror al eliminar Categoria", "/categoria{id}"), HttpStatus.INTERNAL_SERVER_ERROR);	
		
		}
	}
	
}
