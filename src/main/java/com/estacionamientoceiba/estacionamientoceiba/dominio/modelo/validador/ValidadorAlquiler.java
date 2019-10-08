package com.estacionamientoceiba.estacionamientoceiba.dominio.modelo.validador;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;

import com.estacionamientoceiba.estacionamientoceiba.dominio.excepcion.ExcepcionGenerica;
import com.estacionamientoceiba.estacionamientoceiba.dominio.excepcion.ExcepcionVehiculoObligatorio;
import com.estacionamientoceiba.estacionamientoceiba.dominio.modelo.Vehiculo;

public class ValidadorAlquiler {

	private static final String VEHICULO_OBLIGATORIO = "El vehiculo es obligatorio para hacer el registro";
	private static final String HOY_NO_PUEDE_INGRESAR = "El carro no puede ingresar el dia de hoy, por ser de placa iniciada con A.";
	private static final double VALOR_HORA_MOTOS = 500;
	private static final double VALOR_DIA_MOTOS = 4000;
	private static final double VALOR_PAGO_EXTRA_MOTOS = 2000;
	private static final double VALOR_HORA_CARROS = 1000;
	private static final double VALOR_DIA_CARROS = 8000;

	public static void validarVehiculo(long id) {
		if (id <= 0) {
			throw new ExcepcionVehiculoObligatorio(VEHICULO_OBLIGATORIO);
		}
	}

	public static String verificarAccesoPlaca(int tipo, String placa, Date dia) {

		placa = placa.toUpperCase();

		if (tipo == 1 && placa.startsWith("A")) {

			Calendar diaAux = Calendar.getInstance();
			diaAux.setTime(dia);

			int diaSemana = diaAux.get(Calendar.DAY_OF_WEEK);

			if (!(diaSemana == Calendar.MONDAY || diaSemana == Calendar.SUNDAY)) {
				throw new ExcepcionGenerica(HOY_NO_PUEDE_INGRESAR);
			}

		}

		return "Puede ingresar";
	}

	public static double calcularPagoMotos(double cilindraje, Date fechaIngreso, Date fechaSalida) {

		LocalDateTime dateIngreso = fechaIngreso.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
		LocalDateTime dateSalida = fechaSalida.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
		Duration diferencia = Duration.between(dateIngreso, dateSalida);

		long horasConsumidas = diferencia.toHours();
		double valorPagar = 0;

		while (horasConsumidas > 0) {

			if (horasConsumidas < 9) {
				valorPagar += horasConsumidas * VALOR_HORA_MOTOS;
				horasConsumidas -= horasConsumidas;
			} else {
				valorPagar += VALOR_DIA_MOTOS;
				horasConsumidas -= 24;
			}

		}

		if (cilindraje > 500) {
			valorPagar += VALOR_PAGO_EXTRA_MOTOS;
			valorPagar = horasConsumidas == 0 ? (valorPagar + VALOR_HORA_MOTOS) : valorPagar;
		}

		return valorPagar == 0 ? VALOR_HORA_MOTOS : valorPagar;
	}

	public static double calcularPagoCarros(Date fechaIngreso, Date fechaSalida) {

		LocalDateTime dateIngresoCarro = fechaIngreso.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
		LocalDateTime dateSalidaCarro = fechaSalida.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
		Duration diferencia = Duration.between(dateIngresoCarro, dateSalidaCarro);

		long horasConsumidasCarro = diferencia.toHours();
		double valorPagarCarro = 0;

		while (horasConsumidasCarro > 0) {

			if (horasConsumidasCarro < 9) {
				valorPagarCarro += horasConsumidasCarro * VALOR_HORA_CARROS;
				horasConsumidasCarro -= horasConsumidasCarro;
			} else {
				valorPagarCarro += VALOR_DIA_CARROS;
				horasConsumidasCarro -= 24;
			}

		}

		return valorPagarCarro == 0 ? VALOR_HORA_CARROS : valorPagarCarro;
	}

	public boolean verificarCilindrajeMoto(Vehiculo vehiculo) {

		if (vehiculo.getTipo() == 2)
			return vehiculo.getCilindraje() > 500;

		return false;
	}
}
