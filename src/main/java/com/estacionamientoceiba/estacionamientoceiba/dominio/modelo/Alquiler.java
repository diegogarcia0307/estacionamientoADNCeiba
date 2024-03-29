package com.estacionamientoceiba.estacionamientoceiba.dominio.modelo;

import java.util.Date;

import com.estacionamientoceiba.estacionamientoceiba.dominio.modelo.validador.ValidadorAlquiler;

public class Alquiler {

	private Vehiculo vehiculo;
	private Date fechaIngreso;
	private Date fechaSalida;
	private String puesto;
	private double pago;
	public static final double HORA_MOTOS = 1222;

	public Alquiler() {
		vehiculo = new Vehiculo();
		fechaIngreso = new Date(0);
		fechaSalida = null;
		puesto = "<DEFAULT>";
		pago = 0;
	}

	public Alquiler(Vehiculo vehiculo) {
		ValidadorAlquiler.validarVehiculo(vehiculo);
		this.vehiculo = vehiculo;
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

	public Vehiculo getVehiculo() {
		return vehiculo;
	}

	public void setVehiculo(Vehiculo vehiculo) {
		this.vehiculo = vehiculo;
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