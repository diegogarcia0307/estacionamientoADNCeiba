package com.estacionamientoceiba.estacionamientoceiba.aplicacion.comando.manejador.respuestas;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class RespuestaBusqueda {

	String placa;
	int tipoVehiculo;
	Date fechaIngreso;

	public RespuestaBusqueda(String placa2, int tipo, Date fechaIngreso2) {
		this.placa = placa2;
		tipoVehiculo = tipo;
		fechaIngreso = fechaIngreso2;
	}
}
