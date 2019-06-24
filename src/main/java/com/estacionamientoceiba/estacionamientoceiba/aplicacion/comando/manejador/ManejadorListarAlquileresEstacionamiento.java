package com.estacionamientoceiba.estacionamientoceiba.aplicacion.comando.manejador;

import java.util.Collection;

import org.springframework.stereotype.Component;

import com.estacionamientoceiba.estacionamientoceiba.dominio.modelo.Alquiler;
import com.estacionamientoceiba.estacionamientoceiba.dominio.servicio.ServicioListarAlquiler;

@Component
public class ManejadorListarAlquileresEstacionamiento {

	private final ServicioListarAlquiler servicioListarAlquiler;

	public ManejadorListarAlquileresEstacionamiento(ServicioListarAlquiler servicio) {
		this.servicioListarAlquiler = servicio;
	}

	public Collection<Alquiler> ejecutar() {
		return this.servicioListarAlquiler.listar();
	}
}
