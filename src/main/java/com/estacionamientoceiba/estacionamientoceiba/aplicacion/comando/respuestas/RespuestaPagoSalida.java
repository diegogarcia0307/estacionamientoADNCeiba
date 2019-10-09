package com.estacionamientoceiba.estacionamientoceiba.aplicacion.comando.respuestas;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Setter
@Getter
public class RespuestaPagoSalida {

	private double pago;

	public double getPago() {
		return pago;
	}

}
