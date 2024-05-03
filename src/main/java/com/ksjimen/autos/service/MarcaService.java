package com.ksjimen.autos.service;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ksjimen.autos.dto.response.ResponseMarcaDto;
import com.ksjimen.autos.mapper.MarcaMapper;
import com.ksjimen.autos.model.MarcaVehiculo;
import com.ksjimen.autos.repository.IMarcaRepository;

@Service
public class MarcaService {
	
	@Autowired
	private IMarcaRepository repo;
	@Autowired
	private MarcaMapper mapperMarca;
	
	public List<ResponseMarcaDto> listar(){
		List<MarcaVehiculo> marcas = repo.findAll();
		return marcas.stream()
				.map(mapperMarca::marcaToResponseDto)
				.collect(Collectors.toList());
	}

}
