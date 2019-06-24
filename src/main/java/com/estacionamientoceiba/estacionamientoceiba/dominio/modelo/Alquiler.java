package com.estacionamientoceiba.estacionamientoceiba.dominio.modelo;

import java.util.Date;

import com.estacionamientoceiba.estacionamientoceiba.dominio.modelo.validador.ValidadorAlquiler;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Alquiler {

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

		ValidadorAlquiler.validarVehiculo(vehiculo);
		ValidadorAlquiler.verificarPlaca(vehiculo.getTipo(), vehiculo.getPlaca(), fechaIngreso);

		this.vehiculo = vehiculo;
		this.fechaIngreso = fechaIngreso;
		this.fechaSalida = fechaSalida;
		this.puesto = puesto;
		this.pago = pago;
	}

	public Alquiler(Vehiculo vehiculo, Date fechaIngreso) {
		ValidadorAlquiler.validarVehiculo(vehiculo);
		ValidadorAlquiler.verificarPlaca(vehiculo.getTipo(), vehiculo.getPlaca(), fechaIngreso);

		this.vehiculo = vehiculo;
		this.fechaIngreso = fechaIngreso;
	}

}