package com.proyecto.gestion.servicio;

import java.util.List;

import com.proyecto.gestion.dto.CategoriaDTO;
import com.proyecto.gestion.entidad.Categoria;

public interface CategoriaServicio {

	//Listar
	List<Categoria> listarCategorias();
	
	//Agregar
	Categoria agregarCategoria(CategoriaDTO categoriaNueva);
	
	
	Categoria categoriaPorId(Integer id);
	
	
	//Eliminar
	void eliminarCategoria(Integer id);
	
	
	
	
}
