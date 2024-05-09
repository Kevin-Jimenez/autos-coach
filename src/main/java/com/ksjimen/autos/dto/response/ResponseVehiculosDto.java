package com.ksjimen.autos.dto.response;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResponseVehiculosDto {
	private Long idVehiculo;
	private ResponseMarcaDto marca;
	private ResponseLineaDto linea;
	private ResponseTipoVehiculoDto tipoVehiculo;
	private List<ResponseImagenDto> imagenes;
	private Integer modelo;
	private Integer cilindraje;
	private String descripcion;
	private String kilometraje;
	private double precio;
}
