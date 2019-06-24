package com.estacionamientoceiba.estacionamientoceiba.aplicacion.comando;

import java.util.Date;

import com.estacionamientoceiba.estacionamientoceiba.dominio.modelo.Vehiculo;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
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
