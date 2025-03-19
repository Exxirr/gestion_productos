package com.proyecto.gestion.controlador;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.proyecto.gestion.dto.CategoriaDTO;
import com.proyecto.gestion.dto.ProductoDTO;
import com.proyecto.gestion.entidad.Categoria;
import com.proyecto.gestion.servicio.CategoriaServicio;

import lombok.RequiredArgsConstructor;


@RestController
@RequestMapping("/api/v1/")
@RequiredArgsConstructor
public class CategoriaControlador {

	private final CategoriaServicio categoriaServicio;
	
	@GetMapping("categorias")
	@ResponseStatus(HttpStatus.OK)
	public List<CategoriaDTO> todasCategoria(){
		
		List<Categoria> categorias =  categoriaServicio.listarCategorias();
		
		return categorias.stream().map(categoria -> CategoriaDTO.builder()
				.id(categoria.getId())
				.nombre_cat(categoria.getNombre_cat())
				.build())
				.collect(Collectors.toList());
				
				
	
		}
	
	
	
	@PostMapping("categoria")
	@ResponseStatus(HttpStatus.CREATED)
	public Categoria agregarCategoria(@RequestBody CategoriaDTO categoriaDto) {
		
		Categoria categoria = categoriaServicio.agregarCategoria(categoriaDto);
		
		
		return Categoria.builder()
				.id(categoria.getId())
				.nombre_cat(categoria.getNombre_cat())
				.build();
		
	}
	
	@DeleteMapping("categoria/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void eliminarCategoria(@PathVariable Integer id) {
		
		categoriaServicio.eliminarCategoria(id);
	}
	
}
