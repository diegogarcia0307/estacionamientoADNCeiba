package com.estacionamientoceiba.estacionamientoceiba.dominio.modelo.validador;

import java.util.Calendar;
import java.util.Date;

import com.estacionamientoceiba.estacionamientoceiba.dominio.excepcion.ExcepcionCilindrajeCorrecto;
import com.estacionamientoceiba.estacionamientoceiba.dominio.excepcion.ExcepcionGenerica;
import com.estacionamientoceiba.estacionamientoceiba.dominio.excepcion.ExcepcionPlacaObligatoria;
import com.estacionamientoceiba.estacionamientoceiba.dominio.excepcion.ExcepcionTipoVehiculo;

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

	public static void verificarPlaca(String tipo, String placa, Date dia, String mensaje) {
		placa = placa.toUpperCase();

		if (tipo.equalsIgnoreCase("Carro") && placa.startsWith("A")) {

			Calendar diaAux = Calendar.getInstance();
			diaAux.setTime(dia);

			int diaSemana = diaAux.get(Calendar.DAY_OF_WEEK);

			if (!(diaSemana == Calendar.MONDAY || diaSemana == Calendar.SUNDAY)) {
				throw new ExcepcionGenerica(mensaje);
			}

		}
	}
}
