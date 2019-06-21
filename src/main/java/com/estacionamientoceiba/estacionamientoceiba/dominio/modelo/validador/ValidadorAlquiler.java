package com.estacionamientoceiba.estacionamientoceiba.dominio.modelo.validador;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.estacionamientoceiba.estacionamientoceiba.dominio.exepcion.ExcepcionVehiculoObligatorio;
import com.estacionamientoceiba.estacionamientoceiba.dominio.modelo.Alquiler;
import com.estacionamientoceiba.estacionamientoceiba.dominio.modelo.Vehiculo;

public class ValidadorAlquiler {

	public static void validarVehiculo(Object objeto, String mensaje) {
		if (objeto == null) {
			throw new ExcepcionVehiculoObligatorio(mensaje);
		}
	}

	public double calcularPagoMotos(Vehiculo vehiculo, Date fechaIngreso, Date fechaSalida) {

		LocalDateTime dateIngreso = fechaIngreso.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
		LocalDateTime dateSalida = fechaSalida.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
		Duration diferencia = Duration.between(dateIngreso, dateSalida);

		long horasConsumidas = diferencia.toHours();
		double valorPagar = 0;

		if ("Moto".equalsIgnoreCase(vehiculo.getTipo())) {

			while (horasConsumidas > 0) {

				if (horasConsumidas < 9) {
					valorPagar += horasConsumidas * 500;
					horasConsumidas -= horasConsumidas;
				} else {
					valorPagar += 4000;
					horasConsumidas -= 24;
				}

			}

			if (vehiculo.getCilindraje() > 500) {
				valorPagar += 2000;
			}
		}

		return valorPagar;
	}

	public double calcularPagoCarros(Vehiculo vehiculo, Date fechaIngreso, Date fechaSalida) {

		LocalDateTime dateIngresoCarro = fechaIngreso.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
		LocalDateTime dateSalidaCarro = fechaSalida.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
		Duration diferencia = Duration.between(dateIngresoCarro, dateSalidaCarro);

		long horasConsumidasCarro = diferencia.toHours();
		double valorPagarCarro = 0;

		if ("Carro".equalsIgnoreCase(vehiculo.getTipo()))

			while (horasConsumidasCarro > 0) {

				if (horasConsumidasCarro < 9) {
					valorPagarCarro += horasConsumidasCarro * 1000;
					horasConsumidasCarro -= horasConsumidasCarro;
				} else {
					valorPagarCarro += 8000;
					horasConsumidasCarro -= 24;
				}

			}
		return valorPagarCarro;
	}

	public boolean verificarDisponibilidad(List<Alquiler> plazas, int capacidad) {
		return plazas.size() < capacidad;
	}

	public String verificarPlaca(String placa, Date dia) {

		placa = placa.toUpperCase();

		if (placa.startsWith("A")) {

			Calendar diaAux = Calendar.getInstance();
			diaAux.setTime(dia);

			int diaSemana = diaAux.get(Calendar.DAY_OF_WEEK);

			if (!(diaSemana == Calendar.MONDAY || diaSemana == Calendar.SUNDAY)) {
				return "No puede ingresar";
			}

		}

		return "Puede ingresar";
	}

	public boolean verificarCilindrajeMoto(Vehiculo vehiculo) {

		if ("Moto".equalsIgnoreCase(vehiculo.getTipo()))
			return vehiculo.getCilindraje() > 500;

		return false;
	}
}