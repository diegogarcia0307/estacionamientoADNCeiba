package com.estacionamientoceiba.estacionamientoceiba.aplicacion.comando.manejador;

import org.springframework.stereotype.Component;

import com.estacionamientoceiba.estacionamientoceiba.aplicacion.comando.ComandoAlquiler;
import com.estacionamientoceiba.estacionamientoceiba.aplicacion.comando.manejador.respuestas.RespuestaCreacion;
import com.estacionamientoceiba.estacionamientoceiba.dominio.servicio.ServicioCrearAlquilerEstacionamiento;

@Component
public class ManejadorCrearAlquilerEstacionamiento {

	private final ServicioCrearAlquilerEstacionamiento servicioCrearAlquiler;

	public ManejadorCrearAlquilerEstacionamiento(ServicioCrearAlquilerEstacionamiento servicio) {
		servicioCrearAlquiler = servicio;
	}

	public RespuestaCreacion ejecutar(ComandoAlquiler comando)  {
		return this.servicioCrearAlquiler.ejecutar(comando.getVehiculo());
	}
}
