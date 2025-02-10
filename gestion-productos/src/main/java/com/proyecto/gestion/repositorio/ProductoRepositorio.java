package com.proyecto.gestion.repositorio;

import org.springframework.data.repository.CrudRepository;


import com.proyecto.gestion.entidad.Producto;


public interface ProductoRepositorio extends CrudRepository<Producto, Integer> {

}
