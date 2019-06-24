package com.estacionamientoceiba.estacionamientoceiba.dominio.servicio;

import java.util.ArrayList;
import java.util.List;

import com.estacionamientoceiba.estacionamientoceiba.dominio.excepcion.ExcepcionGenerica;
import com.estacionamientoceiba.estacionamientoceiba.dominio.modelo.Alquiler;
import com.estacionamientoceiba.estacionamientoceiba.dominio.modelo.Vehiculo;
import com.estacionamientoceiba.estacionamientoceiba.dominio.repositorio.RepositorioAlquiler;

public class ServicioCrearAlquiler {
	private final String PERMANENCIA = "El vehiculo ya se encuentra dentro del parqueadero";
	private final String NO_HAY_DISPONIBILIDAD = "No hay disponibilidad para el vehiculo que intenta ingresar";
	private RepositorioAlquiler repositorioAlquiler;

	public ServicioCrearAlquiler(RepositorioAlquiler repositorioAlquiler) {
		this.repositorioAlquiler = repositorioAlquiler;
	}

	public void ejecutar(Alquiler alquiler) {
		verificarDisponibilidad(alquiler.getVehiculo().getTipo());
		verificarPermanencia(alquiler);
		new Vehiculo(alquiler.getVehiculo());
		this.repositorioAlquiler.crear(alquiler);
	}

	private void verificarPermanencia(Alquiler alquiler) {
		if (repositorioAlquiler.verificarPermanencia(alquiler))
			throw new ExcepcionGenerica(PERMANENCIA);
	}

	private void verificarDisponibilidad(String tipo) {
		List<Alquiler> plazas = new ArrayList<>(repositorioAlquiler.listar());
		int count = 0;

		for (Alquiler alquiler : plazas) {
			if (alquiler.getVehiculo().getTipo().equalsIgnoreCase(tipo)) {
				count++;
			}
		}

		if ((tipo.equalsIgnoreCase("CARRO") && count >= 20) || (tipo.equalsIgnoreCase("MOTO") && count >= 10)) {
			throw new ExcepcionGenerica(NO_HAY_DISPONIBILIDAD);
		}
	}
}
