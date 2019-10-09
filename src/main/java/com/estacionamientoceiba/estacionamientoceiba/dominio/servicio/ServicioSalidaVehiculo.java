package com.estacionamientoceiba.estacionamientoceiba.dominio.servicio;

import java.util.Date;

import com.estacionamientoceiba.estacionamientoceiba.dominio.excepcion.ExcepcionGenerica;
import com.estacionamientoceiba.estacionamientoceiba.dominio.modelo.Alquiler;
import com.estacionamientoceiba.estacionamientoceiba.dominio.modelo.Vehiculo;
import com.estacionamientoceiba.estacionamientoceiba.dominio.modelo.validador.ValidadorAlquiler;
import com.estacionamientoceiba.estacionamientoceiba.dominio.repositorio.RepositorioAlquiler;

public class ServicioSalidaVehiculo {

	private static final String NO_EXISTE_VEHICULO_ADENTRO = "El vehículo ingresado no se encuentra en el parqueadero.";

	private RepositorioAlquiler repositorioAlquiler;

	public ServicioSalidaVehiculo(RepositorioAlquiler repositorioAlquiler) {
		this.repositorioAlquiler = repositorioAlquiler;
	}

	public double ejecutar(String placa) {

		this.verificarPermanencia(placa);

		Alquiler alquiler = repositorioAlquiler.buscarAlquiler(placa);
		alquiler.setFechaSalida(new Date());

		double valorPago = calcularPago(repositorioAlquiler.buscarVehiculo(placa), alquiler.getFechaIngreso(),
				alquiler.getFechaSalida());

		alquiler.setPago(valorPago);

		repositorioAlquiler.salidaVehiculo(alquiler);

		return valorPago;

	}

	private void verificarPermanencia(String placa) {
		if (!repositorioAlquiler.comprobarPermanenciaVehiculo(placa))
			throw new ExcepcionGenerica(NO_EXISTE_VEHICULO_ADENTRO);
	}

	private double calcularPago(Vehiculo vehiculo, Date fechaIngreso, Date fechaSalida) {
		if (vehiculo.getTipo() == 2) {
			return ValidadorAlquiler.calcularPagoMotos(vehiculo.getCilindraje(), fechaIngreso, fechaSalida);
		} else {
			return ValidadorAlquiler.calcularPagoCarros(fechaIngreso, fechaSalida);
		}
	}

}
