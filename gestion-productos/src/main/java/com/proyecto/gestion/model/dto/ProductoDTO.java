package com.proyecto.gestion.model.dto;



import java.math.BigDecimal;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@Builder //Ayuda a la creacion de objetos con mejor flexibilidad y legibilidad al codigo. 
public class ProductoDTO {

	@PositiveOrZero(message = "El ID debe ser positivo o cero")
	private Integer id;
	
	@NotNull(message = "El nombre del producto no puede ser nulo")
	@NotBlank(message = "El nombre del producto no puede ser blanco")
	@Size(min = 3, max = 50, message = "El nombre debe tener entre 3 y 50 caracteres")
	private String nombre_prod;
	
	
	@Digits(integer = 10, fraction = 2, message = "El precio debe tener hasta 10 digitos enteros y 2 decimales")
	@DecimalMin(value = "0.0", inclusive = false, message = "El precio debe ser mayor a 0")
	private BigDecimal precio_unitario;
	
	@PositiveOrZero(message = "La CANTIDAD debe ser positivo o cero")
	private Integer cantidad;
	
	@PositiveOrZero(message = "El ID categoria debe ser positivo o cero")
	private Integer idCategoria;
	
	
}
