package com.estacionamientoceiba.estacionamientoceiba.dominio.servicio;

import com.estacionamientoceiba.estacionamientoceiba.dominio.modelo.Alquiler;
import com.estacionamientoceiba.estacionamientoceiba.dominio.repositorio.RepositorioAlquiler;

public class ServicioCrearAlquiler {
	private RepositorioAlquiler repositorioAlquiler;

	public ServicioCrearAlquiler(RepositorioAlquiler repositorioAlquiler) {
		this.repositorioAlquiler = repositorioAlquiler;
	}

	public void ejecutar(Alquiler alquiler) {
		this.repositorioAlquiler.crear(alquiler);
	}
}
