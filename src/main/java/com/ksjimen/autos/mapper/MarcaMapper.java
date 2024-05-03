package com.ksjimen.autos.mapper;


import org.mapstruct.Mapper;

import com.ksjimen.autos.dto.response.ResponseMarcaDto;
import com.ksjimen.autos.model.MarcaVehiculo;

@Mapper(componentModel = "spring")
public interface MarcaMapper {
	ResponseMarcaDto marcaToResponseDto(MarcaVehiculo marca);
}
