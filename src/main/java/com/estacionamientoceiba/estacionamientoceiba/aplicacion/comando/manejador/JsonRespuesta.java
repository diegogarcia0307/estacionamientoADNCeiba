package com.estacionamientoceiba.estacionamientoceiba.aplicacion.comando.manejador;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class JsonRespuesta {
	String placa;
	String tipoVehiculo;
	Date fechaIngreso;
}
