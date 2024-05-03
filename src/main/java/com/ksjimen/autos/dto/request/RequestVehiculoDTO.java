package com.ksjimen.autos.dto.request;



import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class RequestVehiculoDTO {
    private Long idVehiculo;
    private Long idMarca;
    private Long idLinea;
    private Long idTipovehiculo;
    private Long modelo;
    private Long cilindraje;
    private String kilometraje;
    private String descripcion;
    private double precio;
}
