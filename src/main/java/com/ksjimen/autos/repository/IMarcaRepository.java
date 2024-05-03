package com.ksjimen.autos.repository;



import org.springframework.data.jpa.repository.JpaRepository;


import com.ksjimen.autos.model.MarcaVehiculo;

public interface IMarcaRepository extends JpaRepository<MarcaVehiculo, Long>{

}
