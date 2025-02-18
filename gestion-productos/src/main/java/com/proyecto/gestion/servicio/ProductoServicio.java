package com.proyecto.gestion.servicio;

import java.util.List;

import com.proyecto.gestion.dto.ProductoDTO;
import com.proyecto.gestion.entidad.Producto;



public interface ProductoServicio {

	//Debe devolver la entidad y enviamos el espejo.
	
	//Listar
	
	List<Producto> listarProductos();
	
	//Agregar
	
	Producto agregarProducto(ProductoDTO productoNuevo);
	
	//Actualizar
	Producto actualizarProducto(ProductoDTO productoActualizado);
	
	//Buscar por Id
	
	Producto buscarProductoPorId(Integer id);
	
	//Eliminar
	void eliminarProducto(Integer id);
	
	
	//Exists 
	boolean existePorId(Integer id);
	
}
