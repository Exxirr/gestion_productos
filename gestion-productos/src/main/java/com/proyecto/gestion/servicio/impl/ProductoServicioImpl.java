package com.proyecto.gestion.servicio.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.proyecto.gestion.entidad.Producto;
import com.proyecto.gestion.repositorio.ProductoRepositorio;
import com.proyecto.gestion.servicio.ProductoServicio;

@Service
public class ProductoServicioImpl implements ProductoServicio {
	
	@Autowired
	private ProductoRepositorio productoRepositorio;
	

	@Override
	public List<Producto> listarProductos() {
		
		return (List<Producto>) productoRepositorio.findAll();
	}

	@Override
	public Producto agregarProducto(Producto productoNuevo) {
		return productoRepositorio.save(productoNuevo);
	}

	@Override
	public Producto actualizarProducto(Producto productoActualizado) {
		return productoRepositorio.save(productoActualizado);
	}

	@Override
	public Producto buscarProductoPorId(Integer id) {
		return productoRepositorio.findById(id).orElse(null);
	}

	@Override
	public void eliminarProducto(Integer id) {
		productoRepositorio.deleteById(id);
		
	}

}
