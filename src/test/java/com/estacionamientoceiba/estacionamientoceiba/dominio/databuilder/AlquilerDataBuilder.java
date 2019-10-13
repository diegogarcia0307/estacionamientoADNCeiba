package com.estacionamientoceiba.estacionamientoceiba.dominio.databuilder;

import java.util.Date;

import com.estacionamientoceiba.estacionamientoceiba.dominio.modelo.Alquiler;

public class AlquilerDataBuilder {

	private long idAlquiler;
	private long idVehiculo;
	private Date fechaIngreso;
	private Date fechaSalida;
	private String puesto;
	private double pago;

	public AlquilerDataBuilder() {
		idVehiculo = 0;
		fechaIngreso = new Date(0);
		fechaSalida = null;
		puesto = "<DEFAULT>";
		pago = 0;
		idAlquiler = 0;
	}

	public AlquilerDataBuilder withVehiculo(long idVehiculo) {
		this.idVehiculo = idVehiculo;
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
		return new Alquiler(idAlquiler, idVehiculo, fechaIngreso, fechaSalida, puesto, pago);
	}
}
