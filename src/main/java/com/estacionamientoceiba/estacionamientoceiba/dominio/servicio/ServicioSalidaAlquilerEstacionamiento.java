package com.estacionamientoceiba.estacionamientoceiba.dominio.servicio;

import com.estacionamientoceiba.estacionamientoceiba.dominio.excepcion.ExcepcionGenerica;
import com.estacionamientoceiba.estacionamientoceiba.dominio.modelo.Alquiler;
import com.estacionamientoceiba.estacionamientoceiba.dominio.modelo.validador.ValidadorAlquiler;
import com.estacionamientoceiba.estacionamientoceiba.dominio.repositorio.RepositorioAlquiler;

public class ServicioSalidaAlquilerEstacionamiento {

	private final String NO_EXISTE = "El vehículo ingresado no se encuentra en el parqueadero";

	private RepositorioAlquiler repositorioAlquiler;

	public ServicioSalidaAlquilerEstacionamiento(RepositorioAlquiler repositorioAlquiler) {
		this.repositorioAlquiler = repositorioAlquiler;
	}

	public double salidaAlquiler(String placa) {
		verificarExistencia(placa);
		double pago= calcularPago(repositorioAlquiler.salidaAlquiler(placa));
		repositorioAlquiler.eliminarPlaza(placa);
		return pago;
	}

	private void verificarExistencia(String placa) {
		if (!repositorioAlquiler.verificarPermanencia(placa))
			throw new ExcepcionGenerica(NO_EXISTE);
	}

	private double calcularPago(Alquiler alquiler) {
		if ("Moto".equalsIgnoreCase(alquiler.getVehiculo().getTipo())) {
			return ValidadorAlquiler.calcularPagoMotos(alquiler.getVehiculo(), alquiler.getFechaIngreso(),
					alquiler.getFechaSalida());
		} else {
			return ValidadorAlquiler.calcularPagoCarros(alquiler.getVehiculo(), alquiler.getFechaIngreso(),
					alquiler.getFechaSalida());
		}
	}

}
