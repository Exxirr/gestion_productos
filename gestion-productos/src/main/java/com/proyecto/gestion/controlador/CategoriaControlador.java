package com.proyecto.gestion.controlador;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.proyecto.gestion.entidad.Categoria;
import com.proyecto.gestion.servicio.CategoriaServicio;


@RestController
@RequestMapping("/api/v1/")
public class CategoriaControlador {

	@Autowired
	private CategoriaServicio categoriaServicio;
	
	@GetMapping("categorias")
	public List<Categoria> todasCategoria(){
		
		return categoriaServicio.listarCategorias();
	}
	
	
	@PostMapping("categoria")
	public Categoria agregarCategoria(@RequestBody Categoria categoria) {
		
		return categoriaServicio.agregarCategoria(categoria);
		
	}
	
	@DeleteMapping("categoria/{id}")
	public void eliminarCategoria(@PathVariable Integer id) {
		
		categoriaServicio.eliminarCategoria(id);
	}
	
}
