package com.proyecto.gestion.model.payload;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@Builder
@ToString
public class MensajeResponse {

	String mensaje;
	
	Object object;
	
}
