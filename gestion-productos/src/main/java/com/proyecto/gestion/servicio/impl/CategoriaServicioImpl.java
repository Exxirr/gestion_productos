package com.proyecto.gestion.servicio.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.proyecto.gestion.model.dto.*;
import com.proyecto.gestion.model.entidad.Categoria;
import com.proyecto.gestion.model.dao.*;
import com.proyecto.gestion.servicio.CategoriaServicio;

@Service
public class CategoriaServicioImpl implements CategoriaServicio {

	@Autowired
	private CategoriaRepositorio categoriaRepositorio;
	
	//Listado
	@Override
	@Transactional(readOnly = true)
	public List<Categoria> listarCategorias() {
		return  categoriaRepositorio.findAll();
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
	
	
	@Override
	public Categoria actualizarCategoria(CategoriaDTO categoriaActualizada) {
		
		
		Categoria categoria = Categoria.builder()
				.id(categoriaActualizada.getId())
				.nombre_cat(categoriaActualizada.getNombre_cat())
				.build();
			
		return categoriaRepositorio.save(categoria);
	}
	

	//Eliminado
	@Override
	@Transactional
	public void eliminarCategoria(Integer id) {
		categoriaRepositorio.deleteById(id);
	}

	@Override
	public Categoria categoriaPorId(Integer id) {
		// TODO Auto-generated method stub
		return categoriaRepositorio.findById(id).orElse(null);
	}

	@Override
	public boolean existePorId(Integer id) {
		// TODO Auto-generated method stub
		return categoriaRepositorio.existsById(id);
	}

	

}
