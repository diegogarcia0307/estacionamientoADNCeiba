package com.estacionamientoceiba.estacionamientoceiba.aplicacion.comando;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ComandoAlquiler {
	private String placa;
	private String marca;
	private String color;
	private double cilindraje;
	private int tipo;
}
