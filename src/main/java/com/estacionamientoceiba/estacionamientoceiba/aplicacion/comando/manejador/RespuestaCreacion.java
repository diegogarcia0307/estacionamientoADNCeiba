package com.estacionamientoceiba.estacionamientoceiba.aplicacion.comando.manejador;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class RespuestaCreacion {

	String placa;
	int tipoVehiculo;
	boolean estadoOperacion;
}
