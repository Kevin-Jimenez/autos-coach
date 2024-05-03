package com.ksjimen.autos.service;

import com.ksjimen.autos.dto.request.RequestVehiculoDTO;
import com.ksjimen.autos.dto.response.ResponseVehiculoDto;
import com.ksjimen.autos.dto.response.ResponseVehiculosDto;
import com.ksjimen.autos.exception.GenericException;
import com.ksjimen.autos.exception.LineaException;
import com.ksjimen.autos.exception.MarcaException;
import com.ksjimen.autos.mapper.VehiculoMapper;
import com.ksjimen.autos.model.LineaVehiculo;
import com.ksjimen.autos.model.MarcaVehiculo;
import com.ksjimen.autos.model.Vehiculo;
import com.ksjimen.autos.repository.ILineaRespository;
import com.ksjimen.autos.repository.IMarcaRepository;
import com.ksjimen.autos.repository.IVehiculoRepository;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class VehiculoService {

    private VehiculoMapper vehiculoMapper;
    private IVehiculoRepository vehiculoRepository;
    private IMarcaRepository marcaRepository;
    private ILineaRespository lineaRespository;

    @Autowired
    public VehiculoService(VehiculoMapper vehiculoMapper,
                           IVehiculoRepository vehiculoRepository, IMarcaRepository marcaRepository,
                           ILineaRespository lineaRespository){
        this.vehiculoMapper = vehiculoMapper;
        this.vehiculoRepository = vehiculoRepository;
        this.marcaRepository = marcaRepository;
        this.lineaRespository = lineaRespository;
    }

    public ResponseVehiculoDto agregarVehiculo(RequestVehiculoDTO requestVehiculoDTO){
        Vehiculo vehiculo = new Vehiculo();
        MarcaVehiculo marcaVehiculo;
        LineaVehiculo lineaVehiculo;
        String response;
        try{
            validaRequestVehiculo(requestVehiculoDTO);
            marcaVehiculo = marcaRepository.findById(
                    requestVehiculoDTO.getIdMarca())
                    .orElseThrow(() -> new MarcaException("La marca de vehiculo no existe")
            );
            lineaVehiculo = lineaRespository.findByIdLineaAndIdMarca(
                    requestVehiculoDTO.getIdLinea() , marcaVehiculo)
                    .orElseThrow(() -> new LineaException("La linea de vehiculo no existe")
            );
            vehiculo = vehiculoMapper.requestVehiculoDtoToVehiculo(requestVehiculoDTO);

            response = agregarVehiculoPersistencia(vehiculo);

            return ResponseVehiculoDto.builder()
                    .idVehiculo(0L)
                    .message(response)
                    .build();
        }catch (Exception e) {
            return ResponseVehiculoDto.builder()
                    .idVehiculo(null)
                    .message("Por favor revisar valores: "+ e.getMessage())
                    .build();
        }
    }

    public String agregarVehiculoPersistencia(Vehiculo vehiculo){
        String result = "";
        try {
        	result = vehiculoRepository.agregarVehiculo(
        			vehiculo.getIdVehiculo(),
                    vehiculo.getIdMarca().getIdMarca(),
                    vehiculo.getIdLinea().getIdLinea(),
                    vehiculo.getIdTipo().getIdTipo(),
                    vehiculo.getModelo(),
                    vehiculo.getCilindraje(),
                    vehiculo.getKilometraje(),
                    vehiculo.getDescripcion(),
                    BigDecimal.valueOf(vehiculo.getPrecio()),
                    result
            );
            return result;
        }catch (Exception e) {
        	e.printStackTrace();
			return result = "Error insertando el Vehiculo";
		}
        
    }

    public void validaRequestVehiculo(RequestVehiculoDTO vehiculoDTO){
        if(vehiculoDTO==null || vehiculoDTO.getIdMarca() == null || vehiculoDTO.getIdLinea() == null){
            throw new GenericException("El request no es valido");
        }
    }

	public Page<ResponseVehiculosDto> listarVehiculos(Pageable pageable) {
		Page<Vehiculo> vehiculos = vehiculoRepository.findAll(pageable);
		List<ResponseVehiculosDto> responses = 
				vehiculos.getContent().stream()
				.map(vehiculoMapper::vehiculoToResponsesDto)
				.collect(Collectors.toList());
		
		return new PageImpl<>(responses, pageable, vehiculos.getTotalElements());
	}



}
