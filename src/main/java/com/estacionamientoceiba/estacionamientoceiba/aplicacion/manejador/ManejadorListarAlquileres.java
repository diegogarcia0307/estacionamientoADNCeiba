package com.estacionamientoceiba.estacionamientoceiba.aplicacion.manejador;

import java.util.Collection;

import org.springframework.stereotype.Component;

import com.estacionamientoceiba.estacionamientoceiba.aplicacion.comando.respuestas.RespuestaListarAlquiler;
import com.estacionamientoceiba.estacionamientoceiba.dominio.modelo.Alquiler;
import com.estacionamientoceiba.estacionamientoceiba.dominio.modelo.Vehiculo;
import com.estacionamientoceiba.estacionamientoceiba.dominio.servicio.ServicioListarAlquiler;
import com.estacionamientoceiba.estacionamientoceiba.dominio.servicio.ServicioListarVehiculo;

@Component
public class ManejadorListarAlquileres {

	private final ServicioListarAlquiler servicioListarAlquiler;
	private final ServicioListarVehiculo servicioListarVehiculo;

	public ManejadorListarAlquileres(ServicioListarAlquiler servicioListarAlquiler,
			ServicioListarVehiculo servicioListarVehiculo) {
		this.servicioListarAlquiler = servicioListarAlquiler;
		this.servicioListarVehiculo = servicioListarVehiculo;
	}

	public Collection<RespuestaListarAlquiler> ejecutar() {
		Collection<Vehiculo> vehiculos = this.servicioListarVehiculo.listar();
		Collection<Alquiler> alquileres = this.servicioListarAlquiler.listar();
		return new RespuestaListarAlquiler().crearListaRespuesta(alquileres, vehiculos);
	}

	public RespuestaListarAlquiler buscarAlquiler(String placa) {
		Alquiler alquiler = this.servicioListarAlquiler.buscarAlquiler(placa);
		Vehiculo vehiculo = this.servicioListarVehiculo.buscarVehiculo(placa);
		return new RespuestaListarAlquiler().crearRespuesta(alquiler, vehiculo);
	}
}
