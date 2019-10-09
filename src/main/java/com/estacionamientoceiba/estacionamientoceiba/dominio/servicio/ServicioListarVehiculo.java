package com.estacionamientoceiba.estacionamientoceiba.dominio.servicio;

import java.util.Collection;

import com.estacionamientoceiba.estacionamientoceiba.dominio.modelo.Vehiculo;
import com.estacionamientoceiba.estacionamientoceiba.dominio.repositorio.RepositorioAlquiler;

public class ServicioListarVehiculo {
	private RepositorioAlquiler repositorioAlquiler;

	public ServicioListarVehiculo(RepositorioAlquiler repositorioAlquiler) {
		this.repositorioAlquiler = repositorioAlquiler;
	}

	public Vehiculo buscarVehiculo(String placa) {
		return repositorioAlquiler.buscarVehiculo(placa);
	}

	public Collection<Vehiculo> listar() {
		return repositorioAlquiler.listarTodoVehiculo();
	}
}
