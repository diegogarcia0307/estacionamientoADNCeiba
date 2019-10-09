package com.estacionamientoceiba.estacionamientoceiba.aplicacion.manejador;

import org.springframework.stereotype.Component;

import com.estacionamientoceiba.estacionamientoceiba.aplicacion.comando.respuestas.RespuestaPagoSalida;
import com.estacionamientoceiba.estacionamientoceiba.dominio.servicio.ServicioSalidaVehiculo;

@Component
public class ManejadorSalidaAlquiler {

	private final ServicioSalidaVehiculo servicioSalida;

	public ManejadorSalidaAlquiler(ServicioSalidaVehiculo servicio) {
		this.servicioSalida = servicio;
	}

	public RespuestaPagoSalida ejecutar(String placa) {
		return new RespuestaPagoSalida(servicioSalida.ejecutar(placa));
	}

}
