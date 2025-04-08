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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.proyecto.gestion.model.dto.CategoriaDTO;
import com.proyecto.gestion.model.entidad.Categoria;
import com.proyecto.gestion.model.payload.MensajeResponse;
import com.proyecto.gestion.servicio.CategoriaServicio;

import lombok.RequiredArgsConstructor;


@RestController
@RequestMapping("/api/v1/")
@RequiredArgsConstructor
public class CategoriaControlador {

	private final CategoriaServicio categoriaServicio;
	
	@GetMapping("categorias")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<?> todasCategoria(){
		
		List<Categoria> categorias =  categoriaServicio.listarCategorias();
		
		List<CategoriaDTO> categoriasLista = categorias.stream().map(categoria -> CategoriaDTO.builder()
				.id(categoria.getId())
				.nombre_cat(categoria.getNombre_cat())
				.build())
				.collect(Collectors.toList());
				
			return new ResponseEntity<>(MensajeResponse.builder()
					.mensaje("Consulta Exitosa")
					.object(categoriasLista)
					.build(), 
					HttpStatus.OK);
		}
	
	
	
	@PostMapping("categoria")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<?> agregarCategoria(@RequestBody CategoriaDTO categoriaDto) {
		
		
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
		
			return new ResponseEntity<>(MensajeResponse.builder()
					.mensaje(exDt.getMessage())
					.object(null)
					.build(), 
					HttpStatus.INTERNAL_SERVER_ERROR);
			
		}	
	}
	
	@DeleteMapping("categoria/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
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
			
			
			return new ResponseEntity<>(MensajeResponse.builder()
					.mensaje(exDt.getMessage())
					.object(null)
					.build(), 
					HttpStatus.INTERNAL_SERVER_ERROR);
		
		}
	}
	
}
