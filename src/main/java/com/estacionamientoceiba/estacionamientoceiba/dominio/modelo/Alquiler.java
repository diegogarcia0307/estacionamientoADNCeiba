package com.estacionamientoceiba.estacionamientoceiba.dominio.modelo;

import java.util.Date;

import com.estacionamientoceiba.estacionamientoceiba.dominio.modelo.validador.ValidadorAlquiler;

public class Alquiler {

	private long idVehiculo;
	private Date fechaIngreso;
	private Date fechaSalida;
	private String puesto;
	private double pago;
	public static final double HORA_MOTOS = 1222;

	public Alquiler() {
		idVehiculo = 0L;
		fechaIngreso = new Date();
		fechaSalida = null;
		puesto = "<DEFAULT>";
		pago = 0;
	}

	public Alquiler(long idVehiculo) {
		ValidadorAlquiler.validarVehiculo(idVehiculo);
		this.idVehiculo = idVehiculo;
	}

	public Alquiler(long idVehiculo, Date fechaIngreso, Date fechaSalida, String puesto, double pago) {

		ValidadorAlquiler.validarVehiculo(idVehiculo);
		// ValidadorAlquiler.verificarPlaca(idVehiculo.getTipo(), idVehiculo.getPlaca(),
		// fechaIngreso);

		this.idVehiculo = idVehiculo;
		this.fechaIngreso = fechaIngreso;
		this.fechaSalida = fechaSalida;
		this.puesto = puesto;
		this.pago = pago;
	}

	public Alquiler(Date fechaIngreso) {
		ValidadorAlquiler.validarVehiculo(idVehiculo);
		// ValidadorAlquiler.verificarPlaca(idVehiculo.getTipo(), idVehiculo.getPlaca(),
		// fechaIngreso);

		// this.idVehiculo = idVehiculo;
		this.fechaIngreso = fechaIngreso;
	}

	public long getidVehiculo() {
		return idVehiculo;
	}

	public void setidVehiculo(long idVehiculo) {
		this.idVehiculo = idVehiculo;
	}

	public Date getFechaIngreso() {
		return fechaIngreso;
	}

	public Date getFechaSalida() {
		return fechaSalida;
	}

	public void setFechaSalida(Date fechaSalida) {
		this.fechaSalida = fechaSalida;
	}

	public String getPuesto() {
		return puesto;
	}

	public double getPago() {
		return pago;
	}

}