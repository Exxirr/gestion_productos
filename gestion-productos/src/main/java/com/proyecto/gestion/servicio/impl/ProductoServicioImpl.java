package com.proyecto.gestion.servicio.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.proyecto.gestion.entidad.Categoria;
import com.proyecto.gestion.entidad.Producto;
import com.proyecto.gestion.repositorio.CategoriaRepositorio;
import com.proyecto.gestion.repositorio.ProductoRepositorio;
import com.proyecto.gestion.servicio.ProductoServicio;

@Service
public class ProductoServicioImpl implements ProductoServicio {
	
	@Autowired
	private ProductoRepositorio productoRepositorio;
	
	@Autowired
	private CategoriaRepositorio categoriaRepositorio;

	@Override
	@Transactional(readOnly = true)
	public List<Producto> listarProductos() {
		
		return (List<Producto>) productoRepositorio.findAll();
	}

	@Override
	@Transactional
	public Producto agregarProducto(Producto productoNuevo) {
		
		Categoria categoria = categoriaRepositorio.findById(productoNuevo.getCategoria().getId()).orElse(null);
		
		productoNuevo.setCategoria(categoria);
		
		return productoRepositorio.save(productoNuevo);
	}

	@Override
	@Transactional
	public Producto actualizarProducto(Producto productoActualizado) {
		
		Categoria categoriaExistente = categoriaRepositorio.findById(productoActualizado.getCategoria().getId()).orElse(null);
		
		productoActualizado.setCategoria(categoriaExistente);
			
		return productoRepositorio.save(productoActualizado);
	}

	@Override
	@Transactional(readOnly = true)
	public Producto buscarProductoPorId(Integer id) {
		return productoRepositorio.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public void eliminarProducto(Integer id) {	
		productoRepositorio.deleteById(id);
		
	}

}
