package com.estacionamientoceiba.estacionamientoceiba.aplicacion.comando.manejador.respuestas;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class RespuestaCreacion {

	String placa;
	int tipoVehiculo;
	boolean estadoOperacion;

	public RespuestaCreacion(String placa, int tipoVehiculo, boolean estadoOperacion) {
		this.placa = placa;
		this.tipoVehiculo = tipoVehiculo;
		this.estadoOperacion = estadoOperacion;
	}

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

	public String getPlaca() {
		return placa;
	}

	public int getTipoVehiculo() {
		return tipoVehiculo;
	}

	public boolean isEstadoOperacion() {
		return estadoOperacion;
	}

}
