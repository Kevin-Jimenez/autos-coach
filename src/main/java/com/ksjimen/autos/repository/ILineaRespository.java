package com.ksjimen.autos.repository;

import com.ksjimen.autos.model.MarcaVehiculo;
import org.springframework.data.jpa.repository.JpaRepository;

import com.ksjimen.autos.model.LineaVehiculo;

import java.util.Optional;

public interface ILineaRespository extends JpaRepository<LineaVehiculo, Long>{
    Optional<LineaVehiculo> findByIdLineaAndIdMarca(Long idLinea, MarcaVehiculo idMarca);
}
