package com.ksjimen.autos.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ksjimen.autos.dto.response.ResponseLineaDto;
import com.ksjimen.autos.exception.LineaException;
import com.ksjimen.autos.service.LineaService;

@RestController
@RequestMapping(value ="/linea")
public class LineaController {
	
	private LineaService lineaService;
	
	public LineaController(LineaService lineaService) {
		this.lineaService = lineaService;
	}
	
	@GetMapping(value = "/")
	public ResponseEntity<List<ResponseLineaDto>> lineas(){
		List<ResponseLineaDto> lineas = new ArrayList<>();
		try {
			lineas = lineaService.listar();
		}catch (Exception e) {
			lineas.add(new ResponseLineaDto(-1L, "Error obteniendo linea"));
			throw new LineaException("LineaController-Lineas: Hubo un error obteniendo lineas "
					+ e.getMessage());
		}
		return ResponseEntity.ok(lineas);
	}

}
