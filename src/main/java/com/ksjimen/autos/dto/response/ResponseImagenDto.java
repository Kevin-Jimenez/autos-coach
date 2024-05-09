package com.ksjimen.autos.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ResponseImagenDto {
	private Long idImagen;
	private Long idVehiculo;
	private String urlImagen;
}
