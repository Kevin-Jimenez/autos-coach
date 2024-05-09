package com.ksjimen.autos.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.ksjimen.autos.dto.response.ResponseLineaDto;
import com.ksjimen.autos.exception.LineaException;
import com.ksjimen.autos.mapper.LineaMapper;
import com.ksjimen.autos.model.LineaVehiculo;
import com.ksjimen.autos.repository.ILineaRespository;

@Service
public class LineaService {
	
	private ILineaRespository lineaRepository;
	private LineaMapper lineaMapper;
	
	public LineaService(ILineaRespository lineaRepository,
						LineaMapper lineaMapper) {
		this.lineaRepository = lineaRepository;
		this.lineaMapper = lineaMapper;
	}

	public List<ResponseLineaDto> listar() {
		List<LineaVehiculo> lineas = new ArrayList<>();
		try {
			lineas = lineaRepository.findAll();
		}catch (Exception e) {
			lineas.add(new LineaVehiculo(-1L, null, "Error Obteniendo Lineas"));
			throw new LineaException("LineaService - listar: Error obteniendo lineas :"+e.toString());
		}
		return lineas.stream().map(lineaMapper::lineaToResponseLineaDto)
				.collect(Collectors.toList());
	}


}
