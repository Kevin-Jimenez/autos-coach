package com.ksjimen.autos.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "imagen_vehiculo")
public class ImagenVehiculo {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_imagen")
	private Long idImagen;
	@ManyToOne
    @JoinColumn(name = "vehiculo_id", referencedColumnName = "id_vehiculo")
    private Vehiculo vehiculoId;
	@Column(name = "url_imagen")
	private String urlImagen;
	@Column(name="estado_registro")
	private String estado;

}
