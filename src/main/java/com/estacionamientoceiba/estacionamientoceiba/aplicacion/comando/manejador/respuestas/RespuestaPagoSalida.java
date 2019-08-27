package com.estacionamientoceiba.estacionamientoceiba.aplicacion.comando.manejador.respuestas;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class RespuestaPagoSalida {

	private double pago;

	public RespuestaPagoSalida(double pago) {
		this.pago = pago;
	}

	public double getPago() {
		return pago;
	}

}
