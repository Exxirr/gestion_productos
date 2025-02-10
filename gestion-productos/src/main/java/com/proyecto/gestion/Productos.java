package com.proyecto.gestion;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "tb_productos")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Productos {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private String nombre_prod;
	
	private Double precio_unitario;
	
	private Integer cantidad;
	
	@ManyToOne
	@JoinColumn(
				name = "categoria_id"
			)
	private Categoria categoria;
	
}
