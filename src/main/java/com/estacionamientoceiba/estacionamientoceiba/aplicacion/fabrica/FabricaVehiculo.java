package com.estacionamientoceiba.estacionamientoceiba.aplicacion.fabrica;

import org.springframework.stereotype.Component;

import com.estacionamientoceiba.estacionamientoceiba.aplicacion.comando.ComandoAlquiler;
import com.estacionamientoceiba.estacionamientoceiba.dominio.modelo.Vehiculo;

@Component
public class FabricaVehiculo {

	private static long ID_VEHICULO = 0;

	public Vehiculo crear(ComandoAlquiler comandoAlquiler) {
		return new Vehiculo(ID_VEHICULO, comandoAlquiler.getTipo(), comandoAlquiler.getPlaca(),
				comandoAlquiler.getCilindraje(), comandoAlquiler.getMarca(), comandoAlquiler.getColor());
	}
}
