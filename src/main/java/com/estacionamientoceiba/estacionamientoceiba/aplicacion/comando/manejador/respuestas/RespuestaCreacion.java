package com.estacionamientoceiba.estacionamientoceiba.aplicacion.comando.manejador.respuestas;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class RespuestaCreacion {

	String placa;
	int tipoVehiculo;
	boolean estadoOperacion;
}
