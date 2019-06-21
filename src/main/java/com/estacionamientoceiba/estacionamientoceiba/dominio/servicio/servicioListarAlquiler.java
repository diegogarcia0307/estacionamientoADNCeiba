package com.estacionamientoceiba.estacionamientoceiba.dominio.servicio;

import java.util.Collection;

import com.estacionamientoceiba.estacionamientoceiba.dominio.modelo.Alquiler;
import com.estacionamientoceiba.estacionamientoceiba.dominio.repositorio.RepositorioAlquiler;

public class servicioListarAlquiler {
	private RepositorioAlquiler repositorioAlquiler;

	public servicioListarAlquiler(RepositorioAlquiler repositorioAlquiler) {
		this.repositorioAlquiler = repositorioAlquiler;
	}

	public Collection<Alquiler> listar() {
		return repositorioAlquiler.listar(); 
	}
}
