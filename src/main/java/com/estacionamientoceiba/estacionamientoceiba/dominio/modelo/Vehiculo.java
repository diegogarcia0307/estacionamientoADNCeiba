package com.estacionamientoceiba.estacionamientoceiba.dominio.modelo;

import com.estacionamientoceiba.estacionamientoceiba.dominio.modelo.validador.ValidadorVehiculo;

public class Vehiculo {

	private String placa;
	private String marca;
	private String color;
	private String tipo;
	private double cilindraje;

	public Vehiculo() {
		placa = marca = tipo = color = "<DEFECTO>";
	}

	public Vehiculo(String tipo, String placa, double cilindraje, String marca, String color) {

		ValidadorVehiculo.validarTipoVehiculo(tipo);
		ValidadorVehiculo.validarPlaca(placa);
		ValidadorVehiculo.validarCilindraje(cilindraje);

		this.tipo = tipo;
		this.placa = placa;
		this.marca = marca;
		this.color = color;
		this.cilindraje = cilindraje;
	}

	public Vehiculo(Vehiculo vehiculo) {

		ValidadorVehiculo.validarTipoVehiculo(vehiculo.getTipo());
		ValidadorVehiculo.validarPlaca(vehiculo.getPlaca());
		ValidadorVehiculo.validarCilindraje(vehiculo.getCilindraje());

		this.tipo = vehiculo.getTipo().toUpperCase();
		this.placa = vehiculo.getPlaca().toUpperCase();
		this.marca = vehiculo.getMarca().toUpperCase();
		this.color = vehiculo.getColor().toUpperCase();
		this.cilindraje = vehiculo.getCilindraje();
	}

	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public double getCilindraje() {
		return cilindraje;
	}

	public void setCilindraje(double cilindraje) {
		this.cilindraje = cilindraje;
	}

}
