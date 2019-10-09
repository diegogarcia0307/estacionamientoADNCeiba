package com.estacionamientoceiba.estacionamientoceiba.aplicacion.manejador;

import org.springframework.stereotype.Component;

import com.estacionamientoceiba.estacionamientoceiba.aplicacion.comando.ComandoAlquiler;
import com.estacionamientoceiba.estacionamientoceiba.aplicacion.comando.respuestas.RespuestaCreacion;
import com.estacionamientoceiba.estacionamientoceiba.aplicacion.fabrica.FabricaVehiculo;
import com.estacionamientoceiba.estacionamientoceiba.dominio.modelo.Vehiculo;
import com.estacionamientoceiba.estacionamientoceiba.dominio.servicio.ServicioCrearAlquiler;

@Component
public class ManejadorCrearAlquiler {

	private final ServicioCrearAlquiler servicioCrearAlquiler;

	public ManejadorCrearAlquiler(ServicioCrearAlquiler servicio) {
		servicioCrearAlquiler = servicio;
	}

	public RespuestaCreacion ejecutar(ComandoAlquiler comando) {
		Vehiculo vehiculo = new FabricaVehiculo().crear(comando);
		long idAlquiler = servicioCrearAlquiler.ejecutar(vehiculo);
		return new RespuestaCreacion(String.valueOf(idAlquiler), idAlquiler > 0);
	}
}
