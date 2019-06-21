package com.estacionamientoceiba.estacionamientoceiba.dominio.modelo.validador;

import com.estacionamientoceiba.estacionamientoceiba.dominio.exepcion.ExcepcionCilindrajeCorrecto;
import com.estacionamientoceiba.estacionamientoceiba.dominio.exepcion.ExcepcionPlacaObligatoria;
import com.estacionamientoceiba.estacionamientoceiba.dominio.exepcion.ExcepcionTipoVehiculo;

public class ValidadorVehiculo {

	public static void validarPlaca(Object objeto, String mensaje) {
		if (objeto == null || ((String) objeto).isEmpty()) {
			throw new ExcepcionPlacaObligatoria(mensaje);
		}
	}

	public static void validarCilindraje(double cilindraje, String mensaje) {
		if (cilindraje < 0) {
			throw new ExcepcionCilindrajeCorrecto(mensaje);
		}

	}

	public static void validarTipoVehiculo(Object tipo, String mensaje) {
		if (tipo == null || ((String) tipo).isEmpty()
				|| !("Carro".equalsIgnoreCase((String) tipo)) && !("Moto".equalsIgnoreCase((String) tipo))) {
			throw new ExcepcionTipoVehiculo(mensaje);
		}
	}
}
