package com.estacionamientoceiba.estacionamientoceiba.aplicacion.comando.manejador.respuestas;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class RespuestaCreacion {

	String placa;
	int tipoVehiculo;
	boolean estadoOperacion;

	public RespuestaCreacion(RespuestaCreacion respuesta) {
		placa = respuesta.getPlaca();
		tipoVehiculo = respuesta.getTipoVehiculo();
		estadoOperacion = respuesta.isEstadoOperacion();
	}

	@Override
	public String toString() {
		return "RespuestaCreacion [placa=" + placa + ", tipoVehiculo=" + tipoVehiculo + ", estadoOperacion="
				+ estadoOperacion + "]";
	}

}
