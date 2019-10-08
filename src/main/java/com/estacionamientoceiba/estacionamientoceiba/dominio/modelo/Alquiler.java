package com.estacionamientoceiba.estacionamientoceiba.dominio.modelo;

import java.util.Date;

import com.estacionamientoceiba.estacionamientoceiba.dominio.modelo.validador.ValidadorAlquiler;

public class Alquiler {

	private long idAlquiler;
	private long idVehiculo;
	private Date fechaIngreso;
	private Date fechaSalida;
	private String puesto;
	private double pago;

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

		this.idVehiculo = idVehiculo;
		this.fechaIngreso = fechaIngreso;
		this.fechaSalida = fechaSalida;
		this.puesto = puesto;
		this.pago = pago;
	}

	public Alquiler(Date fechaIngreso) {
		ValidadorAlquiler.validarVehiculo(idVehiculo);
		this.fechaIngreso = fechaIngreso;
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

	public long getIdVehiculo() {
		return idVehiculo;
	}

	public void setIdVehiculo(long idVehiculo) {
		this.idVehiculo = idVehiculo;
	}

	public void setFechaIngreso(Date fechaIngreso) {
		this.fechaIngreso = fechaIngreso;
	}

	public void setPuesto(String puesto) {
		this.puesto = puesto;
	}

	public void setPago(double pago) {
		this.pago = pago;
	}

	public long getIdAlquiler() {
		return idAlquiler;
	}

	public void setIdAlquiler(long idAlquiler) {
		this.idAlquiler = idAlquiler;
	}

	@Override
	public String toString() {
		return "Alquiler [idAlquiler=" + idAlquiler + ", idVehiculo=" + idVehiculo + ", fechaIngreso=" + fechaIngreso
				+ ", fechaSalida=" + fechaSalida + ", puesto=" + puesto + ", pago=" + pago + "]";
	}

}