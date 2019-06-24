package com.estacionamientoceiba.estacionamientoceiba.aplicacion.comando.manejador;

import org.springframework.stereotype.Component;

import com.estacionamientoceiba.estacionamientoceiba.aplicacion.comando.ComandoAlquiler;
import com.estacionamientoceiba.estacionamientoceiba.dominio.modelo.Alquiler;
import com.estacionamientoceiba.estacionamientoceiba.dominio.servicio.ServicioCrearAlquilerEstacionamiento;

@Component
public class ManejadorCrearAlquilerEstacionamiento {

	private final ServicioCrearAlquilerEstacionamiento servicioCrearAlquiler;

	public ManejadorCrearAlquilerEstacionamiento(ServicioCrearAlquilerEstacionamiento servicio) {
		servicioCrearAlquiler = servicio;
	}

	public boolean ejecutar(ComandoAlquiler comando) {
		return this.servicioCrearAlquiler.ejecutar(comando.getVehiculo());
	}
}
