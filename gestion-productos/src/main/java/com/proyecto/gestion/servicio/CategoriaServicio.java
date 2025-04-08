package com.proyecto.gestion.servicio;

import java.util.List;

import com.proyecto.gestion.model.dto.*;
import com.proyecto.gestion.model.entidad.Categoria;

public interface CategoriaServicio {

	//Listar
	List<Categoria> listarCategorias();
	
	//Agregar
	Categoria agregarCategoria(CategoriaDTO categoriaNueva);
	
	
	Categoria categoriaPorId(Integer id);
	
	
	//Eliminar
	void eliminarCategoria(Integer id);
	
	
	boolean existePorId(Integer id);
	
	
}
