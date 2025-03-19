package com.proyecto.gestion.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;



import com.proyecto.gestion.entidad.Producto;


public interface ProductoRepositorio extends JpaRepository<Producto, Integer> {

}
