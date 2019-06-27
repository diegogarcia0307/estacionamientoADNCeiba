package com.estacionamientoceiba.estacionamientoceiba.dominio.modelo;

import com.estacionamientoceiba.estacionamientoceiba.dominio.modelo.validador.ValidadorVehiculo;

public class Vehiculo {

	private String placa;
	private String marca;
	private String color;
	private double cilindraje;
	/* Tipo = 1 para CARRO, Tipo = 2 para MOTO */
	private int tipo;

	public Vehiculo() {
		placa = marca = color = "<DEFECTO>";
		tipo = 0;
	}

	public Vehiculo(int tipo, String placa, double cilindraje, String marca, String color) {

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

		this.tipo = vehiculo.getTipo();
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

	public int getTipo() {
		return tipo;
	}

	public void setTipo(int tipo) {
		this.tipo = tipo;
	}

	public double getCilindraje() {
		return cilindraje;
	}

	public void setCilindraje(double cilindraje) {
		this.cilindraje = cilindraje;
	}

}
