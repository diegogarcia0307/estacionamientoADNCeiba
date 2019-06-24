package com.estacionamientoceiba.estacionamientoceiba.aplicacion.comando.manejador;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class JsonRespuesta {
	String placa;
	String tipoVehiculo;
	Date fechaIngreso;
}
