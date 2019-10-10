package com.estacionamientoceiba.estacionamientoceiba.aplicacion.manejador;

import java.util.Collection;

import org.springframework.stereotype.Component;

import com.estacionamientoceiba.estacionamientoceiba.aplicacion.comando.respuestas.RespuestaListarAlquiler;
import com.estacionamientoceiba.estacionamientoceiba.aplicacion.fabrica.respuestas.FabricaRespuestaListarAlquiler;
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

	public RespuestaListarAlquiler buscarAlquiler(String placa) {
		Alquiler alquiler = this.servicioListarAlquiler.buscarAlquiler(placa);
		Vehiculo vehiculo = this.servicioListarVehiculo.buscarVehiculo(placa);
		return new FabricaRespuestaListarAlquiler().construir(alquiler, vehiculo);
	}

	public Collection<RespuestaListarAlquiler> listarAlquileresEnUso() {
		Collection<Vehiculo> vehiculos = this.servicioListarVehiculo.listarTodosEnParqueadero();
		Collection<Alquiler> alquileres = this.servicioListarAlquiler.listarAlquileresEnUso();
		return new FabricaRespuestaListarAlquiler().construirColeccion(alquileres, vehiculos);
	}

	public Collection<RespuestaListarAlquiler> listarTodosLosAlquileres() {
		Collection<Vehiculo> vehiculos = this.servicioListarVehiculo.listarTodos();
		Collection<Alquiler> alquileres = this.servicioListarAlquiler.listarTodo();
		return new FabricaRespuestaListarAlquiler().construirColeccion(alquileres, vehiculos);
	}

}
