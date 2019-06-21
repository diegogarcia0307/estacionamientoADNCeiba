package com.estacionamientoceiba.estacionamientoceiba.dominio.modelo;

import java.util.Date;

import com.estacionamientoceiba.estacionamientoceiba.dominio.modelo.validador.ValidadorAlquiler;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Alquiler {

	private static final String VEHICULO_OBLIGATORIO = "El vehiculo es obligatorio para hacer el registro";

	private Vehiculo vehiculo;
	private Date fechaIngreso;
	private Date fechaSalida;
	private String puesto;
	private double pago;

	public Alquiler() {
		vehiculo = new Vehiculo();
		fechaIngreso = new Date(0);
		fechaSalida = null;
		puesto = "<DEFAULT>";
		pago = 0;
	}

	public Alquiler(Vehiculo vehiculo, Date fechaIngreso, Date fechaSalida, String puesto, double pago) {

		ValidadorAlquiler.validarVehiculo(vehiculo, VEHICULO_OBLIGATORIO);

		this.vehiculo = vehiculo;
		this.fechaIngreso = fechaIngreso;
		this.fechaSalida = fechaSalida;
		this.puesto = puesto;
		this.pago = pago;
	}

}