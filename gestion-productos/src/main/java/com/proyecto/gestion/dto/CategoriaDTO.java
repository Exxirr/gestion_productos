package com.proyecto.gestion.dto;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@Builder
public class CategoriaDTO {

	private Integer id;
	
	private String nombre_cat;
	
	
}
