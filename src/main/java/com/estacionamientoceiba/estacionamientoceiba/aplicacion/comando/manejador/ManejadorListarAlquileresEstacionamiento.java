package com.estacionamientoceiba.estacionamientoceiba.aplicacion.comando.manejador;

import java.util.Collection;

import org.springframework.stereotype.Component;

import com.estacionamientoceiba.estacionamientoceiba.dominio.modelo.Alquiler;
import com.estacionamientoceiba.estacionamientoceiba.dominio.repositorio.RepositorioAlquiler;

@Component
public class ManejadorListarAlquileresEstacionamiento {

	private final RepositorioAlquiler repositorioAlquiler;

	public ManejadorListarAlquileresEstacionamiento(RepositorioAlquiler repositorio) {
		this.repositorioAlquiler = repositorio;
	}

	public Collection<Alquiler> ejecutar() {
		return this.repositorioAlquiler.listar();
	}
}
