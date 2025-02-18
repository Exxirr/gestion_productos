package com.proyecto.gestion.servicio.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.proyecto.gestion.dto.CategoriaDTO;
import com.proyecto.gestion.entidad.Categoria;
import com.proyecto.gestion.repositorio.CategoriaRepositorio;
import com.proyecto.gestion.servicio.CategoriaServicio;

@Service
public class CategoriaServicioImpl implements CategoriaServicio {

	@Autowired
	private CategoriaRepositorio categoriaRepositorio;
	
	//Listado
	@Override
	@Transactional(readOnly = true)
	public List<Categoria> listarCategorias() {
		return (List<Categoria>) categoriaRepositorio.findAll();
	}

	//Agregado
	@Override
	@Transactional
	public Categoria agregarCategoria(CategoriaDTO categoriaDto) {
		
		Categoria categoria = Categoria.builder()
				.id(categoriaDto.getId())
				.nombre_cat(categoriaDto.getNombre_cat())
				.build();
		
		
		return categoriaRepositorio.save(categoria);
	}

	//Eliminado
	@Override
	@Transactional
	public void eliminarCategoria(Integer id) {
		categoriaRepositorio.deleteById(id);
	}

}
