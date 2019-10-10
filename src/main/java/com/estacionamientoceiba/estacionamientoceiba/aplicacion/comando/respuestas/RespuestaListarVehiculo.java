package com.estacionamientoceiba.estacionamientoceiba.aplicacion.comando.respuestas;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RespuestaListarVehiculo {
	private long idVehiculo;
	private String placa;
	private String marca;
	private String color;
	private double cilindraje;
	private int tipo;
}
