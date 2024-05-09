package com.ksjimen.autos.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ksjimen.autos.dto.response.ResponseMarcaDto;
import com.ksjimen.autos.exception.MarcaException;
import com.ksjimen.autos.mapper.MarcaMapper;
import com.ksjimen.autos.model.MarcaVehiculo;
import com.ksjimen.autos.repository.IMarcaRepository;

@Service
public class MarcaService {
	
	
	private IMarcaRepository marcaRepository;
	private MarcaMapper mapperMarca;
	
	public MarcaService(IMarcaRepository marcaRepository,
						MarcaMapper mapperMarca) {
		this.marcaRepository = marcaRepository;
		this.mapperMarca = mapperMarca;
	}

	public List<ResponseMarcaDto> listar() {
		List<MarcaVehiculo> marcas = new ArrayList<>();
		try {
			marcas = marcaRepository.findAll();
		}catch (Exception e) {
			marcas.add(new MarcaVehiculo(-1L, "null"));
			throw new MarcaException("Hubo un error Obteniedo las marcas "+e.toString());
		}
		return marcas
				.stream().map(mapperMarca::marcaToResponseMarcaDto)
				.collect(Collectors.toList());
	}

}
