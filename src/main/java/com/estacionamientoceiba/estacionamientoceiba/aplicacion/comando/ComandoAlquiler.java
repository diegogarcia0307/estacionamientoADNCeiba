package com.estacionamientoceiba.estacionamientoceiba.aplicacion.comando;

import com.estacionamientoceiba.estacionamientoceiba.dominio.modelo.Vehiculo;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class ComandoAlquiler {
	private Vehiculo vehiculo;

	public Vehiculo getVehiculo() {
		return vehiculo;
	}

	public void setVehiculo(Vehiculo vehiculo) {
		this.vehiculo = vehiculo;
	}

}
