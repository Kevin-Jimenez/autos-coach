package com.ksjimen.autos.service;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.ksjimen.autos.exception.GenericException;
import com.ksjimen.autos.model.ImagenVehiculo;
import com.ksjimen.autos.model.Vehiculo;
import com.ksjimen.autos.repository.IImagenRepository;
import com.ksjimen.autos.repository.IVehiculoRepository;

@Service
public class ImagenService {
	
	private IVehiculoRepository vehiculoRepository;
	private IImagenRepository imagenReposirtory;
	private final String RUTA_ALMACENAMIENTO = "/src/main/resources/static/imagenes/";
	
	public ImagenService(IVehiculoRepository vehiculoRepository,
						 IImagenRepository imagenRepository) {
		this.vehiculoRepository = vehiculoRepository;
		this.imagenReposirtory = imagenRepository;
	}
	
	public Integer agregarImagenVehiculo(List<MultipartFile> files, Long idVehiculo) {
		ImagenVehiculo respuesta = new ImagenVehiculo();
		try {
			Vehiculo vehiculo = vehiculoRepository.findById(idVehiculo)
					.orElseThrow(() -> new GenericException("No existe vehiculo con el id: "+ idVehiculo));
			String actualPath = System.getProperty("user.dir").replace("\\","/");
			String directory =actualPath+RUTA_ALMACENAMIENTO+idVehiculo+"_vehiculo/";
			String urlImagen;
			File directorio = new File(directory);
			if(!directorio.exists()) {
				directorio.mkdirs();
			}
			
			for(MultipartFile file: files) {
				String nombreImagen = file.getOriginalFilename();
				Path rutaCompleta = Paths.get(directory+nombreImagen);
				Files.write(rutaCompleta, file.getBytes());
				
				urlImagen = "/"+idVehiculo+"_vehiculo/"+nombreImagen;
				ImagenVehiculo imagen = ImagenVehiculo.builder()
						.vehiculoId(vehiculo)
						.urlImagen(urlImagen.toString())
						.estado("A00S")
						.build();
				respuesta = imagenReposirtory.save(imagen);
			}
		}catch (Exception e) {
			respuesta.setIdImagen(-1L);
			throw new GenericException("Hubo un error insertando la imagen del vehiculo: "+ e.getMessage());
		}
		return respuesta.getIdImagen().intValue();
	}

}
