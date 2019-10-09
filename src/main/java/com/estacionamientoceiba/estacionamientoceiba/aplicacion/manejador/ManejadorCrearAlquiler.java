package com.estacionamientoceiba.estacionamientoceiba.aplicacion.manejador;

import org.springframework.stereotype.Component;

import com.estacionamientoceiba.estacionamientoceiba.aplicacion.comando.ComandoAlquiler;
import com.estacionamientoceiba.estacionamientoceiba.aplicacion.comando.respuestas.RespuestaCreacion;
import com.estacionamientoceiba.estacionamientoceiba.dominio.servicio.ServicioCrearAlquiler;

@Component
public class ManejadorCrearAlquiler {

	private final ServicioCrearAlquiler servicioCrearAlquiler;

	public ManejadorCrearAlquiler(ServicioCrearAlquiler servicio) {
		servicioCrearAlquiler = servicio;
	}

	public RespuestaCreacion ejecutar(ComandoAlquiler comando) {
		long idAlquiler = servicioCrearAlquiler.ejecutar(comando.getVehiculo());
		return new RespuestaCreacion(String.valueOf(idAlquiler), idAlquiler > 0);
	}
}
