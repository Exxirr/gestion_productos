package com.proyecto.gestion.model.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.proyecto.gestion.model.entidad.Producto;


public interface ProductoRepositorio extends JpaRepository<Producto, Integer> {

}
