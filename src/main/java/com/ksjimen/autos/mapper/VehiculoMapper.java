package com.ksjimen.autos.mapper;

import com.ksjimen.autos.dto.request.RequestVehiculoDTO;
import com.ksjimen.autos.dto.response.ResponseVehiculosDto;
import com.ksjimen.autos.model.LineaVehiculo;
import com.ksjimen.autos.model.MarcaVehiculo;
import com.ksjimen.autos.model.TipoVehiculo;
import com.ksjimen.autos.model.Vehiculo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface VehiculoMapper {
    VehiculoMapper Instance = Mappers.getMapper(VehiculoMapper.class);

    @Mapping(source = "idMarca", target = "idMarca", qualifiedByName = "toMarcaVehiculo")
    @Mapping(source = "idLinea", target = "idLinea", qualifiedByName = "toLineaVehiculo")
    @Mapping(source = "idTipovehiculo", target = "idTipo", qualifiedByName ="toTipoVehiculo")
    Vehiculo requestVehiculoDtoToVehiculo(RequestVehiculoDTO requestVehiculoDTO);

    @Mapping(source = "idMarca", target = "idMarca", qualifiedByName = "MarcaToLong")
    @Mapping(source = "idLinea", target = "idLinea", qualifiedByName = "LineaToLong")
    @Mapping(source = "idTipo", target = "idTipovehiculo", qualifiedByName = "TipoToLong")
    RequestVehiculoDTO vehiculoToRequestVehiculoDTO(Vehiculo vehiculo);
    
    @Mapping(source="idMarca", target = "marca")
    @Mapping(source="idLinea", target = "linea")
    @Mapping(source="idTipo", target = "tipoVehiculo")
    ResponseVehiculosDto vehiculoToResponsesDto(Vehiculo vehiculo);
    

    @Named("toMarcaVehiculo")
    default MarcaVehiculo toMarcaVehiculo(Long idMarca) {
        MarcaVehiculo marcaVehiculo = new MarcaVehiculo();
        marcaVehiculo.setIdMarca(idMarca);
        return marcaVehiculo;
    }
    
    @Named("toLineaVehiculo")
    default LineaVehiculo toLineaVehiculo(Long idLinea) {
    	LineaVehiculo lineaVehiculo = new LineaVehiculo();
    	lineaVehiculo.setIdLinea(idLinea);
    	return lineaVehiculo;
    }
    
    @Named("toTipoVehiculo")
    default TipoVehiculo toTipoVehiculo(Long idTipo) {
        TipoVehiculo tipoVehiculo = new TipoVehiculo();
        tipoVehiculo.setIdTipo(idTipo);
        return tipoVehiculo;
    }
    
    @Named("MarcaToLong")
    default Long toLong(MarcaVehiculo marcaVehiculo) {
        return marcaVehiculo != null ? marcaVehiculo.getIdMarca() : null;
    }
    
    @Named("LineaToLong")
    default Long toLong(LineaVehiculo lineaVehiculo) {
        return lineaVehiculo != null ? lineaVehiculo.getIdLinea() : null;
    }
    
    @Named("TipoToLong")
    default Long toLong(TipoVehiculo tipoVehiculo) {
        return tipoVehiculo != null ? tipoVehiculo.getIdTipo() : null;
    }
}
