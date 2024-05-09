package com.ksjimen.autos.controller;

import com.ksjimen.autos.dto.request.RequestVehiculoDTO;
import com.ksjimen.autos.dto.response.ResponseVehiculoDto;
import com.ksjimen.autos.dto.response.ResponseVehiculosDto;
import com.ksjimen.autos.service.VehiculoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("*")
@RequestMapping(value = "/vehiculo")
public class VehiculoController {

	private VehiculoService vehiculoService;

	@Autowired
	public VehiculoController(VehiculoService vehiculoService){
		this.vehiculoService = vehiculoService;
	}


	@PostMapping(value = "/")
	public ResponseEntity<ResponseVehiculoDto> agregarVehiculo(@RequestBody RequestVehiculoDTO vehiculoDTO){
		return ResponseEntity.ok(vehiculoService.agregarVehiculo(vehiculoDTO));
	}
	
	
	@GetMapping(value = "/")
	public ResponseEntity<Page<ResponseVehiculosDto>> listarVehiculos(Pageable pageable){
		Page<ResponseVehiculosDto> vehiculos = vehiculoService.listarVehiculos(pageable);
		return ResponseEntity.ok(vehiculos);
	}
	
	

}
