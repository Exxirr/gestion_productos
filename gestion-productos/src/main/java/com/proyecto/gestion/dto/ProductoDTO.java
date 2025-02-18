package com.proyecto.gestion.dto;



import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@Builder //Ayuda a la creacion de objetos con mejor flexibilidad y legibilidad al codigo. 
public class ProductoDTO {

	
	private Integer id;
	
	private String nombre_prod;
	
	private Double precio_unitario;
	
	private Integer cantidad;
	
	private Integer idCategoria;
	
	
}
