package com.estacionamientoceiba.estacionamientoceiba.dominio.databuilder;

import java.util.Date;

import com.estacionamientoceiba.estacionamientoceiba.dominio.modelo.Alquiler;
import com.estacionamientoceiba.estacionamientoceiba.dominio.modelo.Vehiculo;

public class AlquilerDataBuilder {

	private Vehiculo vehiculo;
	private Date fechaIngreso;
	private Date fechaSalida;
	private String puesto;
	private double pago;

	public AlquilerDataBuilder() {
		vehiculo = new Vehiculo();
		fechaIngreso = new Date(0);
		fechaSalida = null;
		puesto = "<DEFAULT>";
		pago = 0;
	}

	public AlquilerDataBuilder withVehiculo(Vehiculo vehiculo) {
		this.vehiculo = vehiculo;
		return this;
	}

	public AlquilerDataBuilder withFechaIngreso(Date fecha) {
		this.fechaIngreso = fecha;
		return this;
	}

	public AlquilerDataBuilder withFechaSalida(Date fecha) {
		this.fechaSalida = fecha;
		return this;
	}

	public AlquilerDataBuilder withPuesto(String puesto) {
		this.puesto = puesto;
		return this;
	}

	public AlquilerDataBuilder withPago(double pago) {
		this.pago = pago;
		return this;
	}

	public Alquiler buildAlquiler() {
		return new Alquiler(vehiculo, fechaIngreso, fechaSalida, puesto, pago);
	}
}
