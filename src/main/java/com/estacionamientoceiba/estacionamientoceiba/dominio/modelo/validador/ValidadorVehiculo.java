package com.estacionamientoceiba.estacionamientoceiba.dominio.modelo.validador;

import com.estacionamientoceiba.estacionamientoceiba.dominio.excepcion.ExcepcionCilindrajeCorrecto;
import com.estacionamientoceiba.estacionamientoceiba.dominio.excepcion.ExcepcionPlacaObligatoria;
import com.estacionamientoceiba.estacionamientoceiba.dominio.excepcion.ExcepcionTipoVehiculo;

public class ValidadorVehiculo {

	public ValidadorVehiculo() {
	}

	private static final String PLACA_OBLIGATORIA = "La placa es obligatoria";
	private static final String TIPO_VEHICULO_OBLIGATORIO = "El tipo de vehiculo es obligatorio";
	private static final String CILINDRAJE_CORRECTO = "El valor del cilindraje es obligatorio y su valor debe ser positivo";

	public static void validarPlaca(Object objeto) {
		if (objeto == null || ((String) objeto).isEmpty()) {
			throw new ExcepcionPlacaObligatoria(PLACA_OBLIGATORIA);
		}
	}

	public static void validarCilindraje(double cilindraje) {
		if (cilindraje < 0) {
			throw new ExcepcionCilindrajeCorrecto(CILINDRAJE_CORRECTO);
		}

	}

	public static void validarTipoVehiculo(Object tipo) {
		if (tipo == null || ((String) tipo).isEmpty()
				|| !("Carro".equalsIgnoreCase((String) tipo)) && !("Moto".equalsIgnoreCase((String) tipo))) {
			throw new ExcepcionTipoVehiculo(TIPO_VEHICULO_OBLIGATORIO);
		}
	}

}
