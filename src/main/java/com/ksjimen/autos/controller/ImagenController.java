package com.ksjimen.autos.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ksjimen.autos.service.ImagenService;

@RestController
@RequestMapping(value = "/imagenes/")
public class ImagenController {
	
	private ImagenService imagenService;
	
	public ImagenController(ImagenService imagenService) {
		this.imagenService = imagenService;
	}
	
	@PostMapping(value = "/{idVehiculo}")
	public ResponseEntity<Integer> agregarImagenVehiculo(@RequestParam("file") List<MultipartFile> files, @PathVariable Long idVehiculo){
		Integer idImage;
		try {
			idImage = imagenService.agregarImagenVehiculo(files,idVehiculo);
		}catch (Exception e) {
			idImage = -1;
		}
		return ResponseEntity.ok(idImage);
	}

}
