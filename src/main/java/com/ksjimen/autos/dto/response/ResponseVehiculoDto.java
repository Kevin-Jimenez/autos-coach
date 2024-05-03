package com.ksjimen.autos.dto.response;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ResponseVehiculoDto {
	private Long idVehiculo;
	private String message;
}
