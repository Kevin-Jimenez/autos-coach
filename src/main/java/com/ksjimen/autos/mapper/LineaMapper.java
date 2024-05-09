package com.ksjimen.autos.mapper;

import org.mapstruct.Mapper;

import com.ksjimen.autos.dto.response.ResponseLineaDto;
import com.ksjimen.autos.model.LineaVehiculo;

@Mapper(componentModel = "spring")
public interface LineaMapper {
	ResponseLineaDto lineaToResponseLineaDto(LineaVehiculo linea);

}
