package com.ksjimen.autos.model;


import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinColumns;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "vehiculo")
public class Vehiculo {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_vehiculo")
	private Long idVehiculo;
	@Column(name = "modelo")
	private Integer modelo;
	@Column(name = "cilindraje")
	private Integer cilindraje;
	@Column(name = "descripcion")
	private String descripcion;
	@Column(name = "kilometraje")
	private String kilometraje;
	@Column(name = "precio")
	private double precio;
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumns({
		@JoinColumn(name = "marca_id", referencedColumnName = "id_marca")
		})
	private MarcaVehiculo idMarca;
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumns({
		@JoinColumn(name = "linea_id", referencedColumnName = "id_linea")
		})
	private LineaVehiculo idLinea;
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumns({
		@JoinColumn(name = "tipo_vehiculo_id", referencedColumnName = "id_tipo")
	})
	private TipoVehiculo idTipo;
	
	@OneToMany(mappedBy = "vehiculoId")
	private List<ImagenVehiculo> imagenes;
}
