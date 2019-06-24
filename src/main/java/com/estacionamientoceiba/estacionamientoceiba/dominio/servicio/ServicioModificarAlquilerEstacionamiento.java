package com.estacionamientoceiba.estacionamientoceiba.dominio.servicio;

import com.estacionamientoceiba.estacionamientoceiba.dominio.modelo.Alquiler;
import com.estacionamientoceiba.estacionamientoceiba.dominio.repositorio.RepositorioAlquiler;

public class ServicioModificarAlquilerEstacionamiento {

	private RepositorioAlquiler repositorioAlquiler;

	public ServicioModificarAlquilerEstacionamiento(RepositorioAlquiler repositorioAlquiler) {
		this.repositorioAlquiler = repositorioAlquiler;
	}

	public boolean modificarAlquiler(Alquiler alquiler) {
		return repositorioAlquiler.modificar(alquiler.getVehiculo().getPlaca());
	}

	/*
	 * private boolean verificarExistencia(Vehiculo vehiculo) {
	 * 
	 * }
	 */

}
