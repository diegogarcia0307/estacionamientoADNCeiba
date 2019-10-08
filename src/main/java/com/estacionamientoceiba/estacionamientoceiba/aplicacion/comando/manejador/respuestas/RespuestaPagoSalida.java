package com.estacionamientoceiba.estacionamientoceiba.aplicacion.comando.manejador.respuestas;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
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
