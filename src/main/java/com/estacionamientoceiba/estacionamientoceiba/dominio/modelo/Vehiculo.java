package com.estacionamientoceiba.estacionamientoceiba.dominio.modelo;

import com.estacionamientoceiba.estacionamientoceiba.dominio.modelo.validador.ValidadorVehiculo;

public class Vehiculo {

	private static final String PLACA_OBLIGATORIA = "La placa es obligatoria";
	private static final String TIPO_VEHICULO_OBLIGATORIO = "El tipo de vehiculo es obligatorio";
	private static final String CILINDRAJE_CORRECTO = "El valor del cilindraje es obligatorio y su valor debe ser positivo";

	private String placa;
	private String marca;
	private String color;
	private String tipo;
	private double cilindraje;

	public Vehiculo() {
		placa = marca = tipo = color = "<DEFECTO>";
	}

	public Vehiculo(String tipo, String placa, double cilindraje, String marca, String color) {

		ValidadorVehiculo.validarTipoVehiculo(tipo, TIPO_VEHICULO_OBLIGATORIO);
		ValidadorVehiculo.validarPlaca(placa, PLACA_OBLIGATORIA);
		ValidadorVehiculo.validarCilindraje(cilindraje, CILINDRAJE_CORRECTO);

		this.tipo = tipo;
		this.placa = placa;
		this.marca = marca;
		this.color = color;
		this.cilindraje = cilindraje;
	}

	public Vehiculo(Vehiculo vehiculo) {

		ValidadorVehiculo.validarTipoVehiculo(vehiculo.getTipo(), TIPO_VEHICULO_OBLIGATORIO);
		ValidadorVehiculo.validarPlaca(vehiculo.getPlaca(), PLACA_OBLIGATORIA);
		ValidadorVehiculo.validarCilindraje(vehiculo.getCilindraje(), CILINDRAJE_CORRECTO);

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
