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

	public boolean salidaAlquiler(String placa) {
		verificarExistencia(placa);
		return (repositorioAlquiler.salidaAlquiler(placa));
	}

	private void verificarExistencia(String placa) {
		if (!repositorioAlquiler.verificarPermanencia(placa))
			throw new ExcepcionGenerica(NO_EXISTE);
	}

	/*
	 * private double calcularPago(Alquiler alquiler) { if
	 * ("Moto".equalsIgnoreCase(alquiler.getVehiculo().getTipo())) {
	 * ValidadorAlquiler.calcularPagoMotos(alquiler.getVehiculo(),
	 * alquiler.getFechaIngreso(), ); } }
	 */

}
