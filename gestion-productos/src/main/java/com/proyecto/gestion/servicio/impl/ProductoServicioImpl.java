package com.proyecto.gestion.servicio.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.proyecto.gestion.dto.ProductoDTO;
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

	//Listado
	@Override
	@Transactional(readOnly = true)
	public List<Producto> listarProductos() {
		
		return (List<Producto>) productoRepositorio.findAll();
	}

	//Agregado
	@Override
	@Transactional
	public Producto agregarProducto(ProductoDTO productoNuevo) {
		
		Categoria categoria = categoriaRepositorio.findById(productoNuevo.getIdCategoria()).orElse(null);
		
		Producto producto = Producto.builder()
				.id(productoNuevo.getId())
				.nombre_prod(productoNuevo.getNombre_prod())
				.precio_unitario(productoNuevo.getPrecio_unitario())
				.cantidad(productoNuevo.getCantidad())
				.categoria(categoria)
				.build();
		
		return productoRepositorio.save(producto);
	}

	//Actualizado
	@Override
	@Transactional
	public Producto actualizarProducto(ProductoDTO productoActualizado) {
		
		
		Categoria categoriaExistente = categoriaRepositorio.findById(productoActualizado.getIdCategoria()).orElse(null);
		
		
		Producto producto = Producto.builder()
				.id(productoActualizado.getId())
				.nombre_prod(productoActualizado.getNombre_prod())
				.precio_unitario(productoActualizado.getPrecio_unitario())
				.cantidad(productoActualizado.getCantidad())
				.categoria(categoriaExistente)
				.build();
		
		return productoRepositorio.save(producto);
	}

	//Listado por Id
	@Override
	@Transactional(readOnly = true)
	public Producto buscarProductoPorId(Integer id) {
		return productoRepositorio.findById(id).orElse(null);
	}

	//Eliminado
	@Override
	@Transactional
	public void eliminarProducto(Integer id) {	
		productoRepositorio.deleteById(id);
		
	}

	//Existente ID
	@Override
	public boolean existePorId(Integer id) {
		
		return productoRepositorio.existsById(id);
	}
	
	

}
