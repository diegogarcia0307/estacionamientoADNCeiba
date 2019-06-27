package com.estacionamientoceiba.estacionamientoceiba.aplicacion.comando.manejador;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class JsonRespuestaCrear {

	String placa;
	int tipoVehiculo;
	boolean estadoOperacion;
}
