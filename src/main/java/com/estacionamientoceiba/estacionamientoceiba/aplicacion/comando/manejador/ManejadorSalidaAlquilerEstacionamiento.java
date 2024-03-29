package com.estacionamientoceiba.estacionamientoceiba.aplicacion.comando.manejador;

import org.springframework.stereotype.Component;

import com.estacionamientoceiba.estacionamientoceiba.aplicacion.comando.manejador.respuestas.RespuestaPagoSalida;
import com.estacionamientoceiba.estacionamientoceiba.dominio.servicio.ServicioSalidaAlquilerEstacionamiento;

@Component
public class ManejadorSalidaAlquilerEstacionamiento {

	private final ServicioSalidaAlquilerEstacionamiento servicioSalida;

	public ManejadorSalidaAlquilerEstacionamiento(ServicioSalidaAlquilerEstacionamiento servicio) {
		this.servicioSalida = servicio;
	}

	public RespuestaPagoSalida salidaAlquiler(String placa) {
		return servicioSalida.salidaAlquiler(placa);
	}

}
