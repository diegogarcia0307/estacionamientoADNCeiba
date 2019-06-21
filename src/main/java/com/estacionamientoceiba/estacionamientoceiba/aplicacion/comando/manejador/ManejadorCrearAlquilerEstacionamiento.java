package com.estacionamientoceiba.estacionamientoceiba.aplicacion.comando.manejador;

import org.springframework.stereotype.Component;

import com.estacionamientoceiba.estacionamientoceiba.aplicacion.comando.ComandoAlquiler;
import com.estacionamientoceiba.estacionamientoceiba.dominio.modelo.Alquiler;
import com.estacionamientoceiba.estacionamientoceiba.dominio.servicio.ServicioCrearAlquiler;

@Component
public class ManejadorCrearAlquilerEstacionamiento {

	private final ServicioCrearAlquiler servicioCrearAlquiler;

	public ManejadorCrearAlquilerEstacionamiento(ServicioCrearAlquiler servicio) {
		servicioCrearAlquiler = servicio;
	}

	public void ejecutar(ComandoAlquiler comando) {
		this.servicioCrearAlquiler.ejecutar(new Alquiler(comando.getVehiculo(), comando.getFechaIngreso(),
				comando.getFechaSalida(), comando.getPuesto(), comando.getPago()));
	}
}
