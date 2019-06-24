package com.estacionamientoceiba.estacionamientoceiba.aplicacion.comando;

import com.estacionamientoceiba.estacionamientoceiba.dominio.modelo.Vehiculo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ComandoAlquiler {
	private Vehiculo vehiculo;
}
