package com.estacionamientoceiba.estacionamientoceiba.aplicacion.comando.manejador;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class RespuestaBusqueda {
	String placa;
	int tipoVehiculo;
	Date fechaIngreso;
}
