package com.ksjimen.autos.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ksjimen.autos.dto.response.ResponseMarcaDto;
import com.ksjimen.autos.exception.GenericException;
import com.ksjimen.autos.exception.MarcaException;
import com.ksjimen.autos.service.MarcaService;

@RestController
@RequestMapping(value = "/marcas")
public class MarcaController {
	
	private MarcaService marcaService;
	
	public MarcaController(MarcaService marcaService) {
		this.marcaService = marcaService;
	}
	
	@GetMapping(value = "/")
	public ResponseEntity<List<ResponseMarcaDto>> listarMarcas(){
		List<ResponseMarcaDto> marcas = new ArrayList<>();
		try {
			marcas = marcaService.listar();
		}catch (Exception e) {
			marcas.add(new ResponseMarcaDto(-1L, "Hubo un error obteniendo las marcas"));
			throw new MarcaException("listarMarcas-controller: Hubo un error obteniendo las marcas"
					+ e.getMessage());
		}
		return ResponseEntity.ok(marcas);
	}

}
