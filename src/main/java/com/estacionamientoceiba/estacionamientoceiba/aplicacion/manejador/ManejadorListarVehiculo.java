package com.estacionamientoceiba.estacionamientoceiba.aplicacion.manejador;

import java.util.Collection;

import org.springframework.stereotype.Component;

import com.estacionamientoceiba.estacionamientoceiba.aplicacion.comando.respuestas.RespuestaListarVehiculo;
import com.estacionamientoceiba.estacionamientoceiba.aplicacion.fabrica.respuestas.FabricaRespuestaListarVehiculo;
import com.estacionamientoceiba.estacionamientoceiba.dominio.modelo.Vehiculo;
import com.estacionamientoceiba.estacionamientoceiba.dominio.servicio.ServicioListarVehiculo;

@Component
public class ManejadorListarVehiculo {

	private ServicioListarVehiculo servicioListarVehiculo;

	public ManejadorListarVehiculo(ServicioListarVehiculo servicioListarVehiculo) {
		this.servicioListarVehiculo = servicioListarVehiculo;
	}

	public RespuestaListarVehiculo buscarVehiculo(String placa) {
		Vehiculo vehiculo = servicioListarVehiculo.buscarVehiculo(placa);
		return new FabricaRespuestaListarVehiculo().construir(vehiculo);
	}

	public Collection<RespuestaListarVehiculo> listarVehiculosEnParqueadero() {
		Collection<Vehiculo> vehiculos = servicioListarVehiculo.listarTodosEnParqueadero();
		return new FabricaRespuestaListarVehiculo().construirColeccion(vehiculos);
	}

	public Collection<RespuestaListarVehiculo> listarTodosLosVehiculos() {
		Collection<Vehiculo> vehiculos = servicioListarVehiculo.listarTodos();
		return new FabricaRespuestaListarVehiculo().construirColeccion(vehiculos);
	}

}
