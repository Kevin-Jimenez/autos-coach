package com.ksjimen.autos.repository;

import com.ksjimen.autos.model.Vehiculo;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;

public interface IVehiculoRepository extends JpaRepository<Vehiculo,Long> {

	@Query(value = "CALL AGREGAR_VEHICULO(:idVehiculo,:idMarca,:idLinea,:idTipo,:modelo,:cilindraje,:kilometraje,:descripcion,:precio,:resultado)",
	           nativeQuery = true)
	 String agregarVehiculo(
	        @Param("idVehiculo") Long idVehiculo,
	        @Param("idMarca") Long idMarca,
	        @Param("idLinea") Long idLinea,
	        @Param("idTipo") Long idTipo,
	        @Param("modelo") Integer modelo,
	        @Param("cilindraje") Integer cilindraje,
	        @Param("kilometraje") String kilometraje,
	        @Param("descripcion") String descripcion,
	        @Param("precio") BigDecimal precio,
	        @Param("resultado") String resultado
	    );
}
