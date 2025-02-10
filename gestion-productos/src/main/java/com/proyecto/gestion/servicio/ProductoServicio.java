package com.proyecto.gestion.servicio;

import java.util.List;

import com.proyecto.gestion.entidad.Producto;

public interface ProductoServicio {

	//Listar
	List<Producto> listarProductos();
	
	//Agregar
	Producto agregarProducto(Producto productoNuevo);
	
	//Actualizar
	Producto actualizarProducto(Producto productoActualizado);
	
	//Buscar por Id
	Producto buscarProductoPorId(Integer id);
	
	//Eliminar
	void eliminarProducto(Integer id);
	
	
}
