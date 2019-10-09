package com.estacionamientoceiba.estacionamientoceiba.dominio.servicio;

import java.util.Collection;

import com.estacionamientoceiba.estacionamientoceiba.dominio.modelo.Alquiler;
import com.estacionamientoceiba.estacionamientoceiba.dominio.repositorio.RepositorioAlquiler;

public class ServicioListarAlquiler {
	private RepositorioAlquiler repositorioAlquiler;

	public ServicioListarAlquiler(RepositorioAlquiler repositorioAlquiler) {
		this.repositorioAlquiler = repositorioAlquiler;
	}

	public Alquiler buscarAlquiler(String placa) {
		return repositorioAlquiler.buscarAlquiler(placa);
	}

	public Collection<Alquiler> listar() {
		return repositorioAlquiler.listarTodoAlquiler();
	}
}
