package com.proyecto.gestion.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@Builder
public class CategoriaDTO {

	@PositiveOrZero(message = "El ID debe ser positivo o cero")
	private Integer id;
	
	
	@NotNull(message = "El nombre del categoria no puede ser nulo")
	@NotBlank(message = "El nombre del categoria no puede ser blanco")
	@Size(min = 3, max = 50, message = "El nombre debe tener entre 3 y 50 caracteres")
	private String nombre_cat;
	
	
}
