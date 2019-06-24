package com.estacionamientoceiba.estacionamientoceiba.aplicacion.comando.manejador;

import java.util.Collection;

import org.springframework.stereotype.Component;

import com.estacionamientoceiba.estacionamientoceiba.dominio.modelo.Alquiler;
import com.estacionamientoceiba.estacionamientoceiba.dominio.servicio.ServicioListarAlquilerEstacionamiento;

@Component
public class ManejadorListarAlquileresEstacionamiento {

	private final ServicioListarAlquilerEstacionamiento servicioListarAlquiler;

	public ManejadorListarAlquileresEstacionamiento(ServicioListarAlquilerEstacionamiento servicio) {
		this.servicioListarAlquiler = servicio;
	}

	public Collection<Alquiler> ejecutar() {
		return this.servicioListarAlquiler.listar();
	}
}
