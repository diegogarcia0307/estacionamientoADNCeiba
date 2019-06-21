package com.estacionamientoceiba.estacionamientoceiba.dominio.databuilder;

import com.estacionamientoceiba.estacionamientoceiba.dominio.modelo.Vehiculo;

public class VehiculoDataBuilder {

	private String placa;
	private String marca;
	private String color;
	private String tipo;
	private double cilindraje;

	public VehiculoDataBuilder() {
		placa = marca = tipo = color = "<DEFECTO>";
	}

	public VehiculoDataBuilder withPlaca(String placa) {
		this.placa = placa;
		return this;
	}

	public VehiculoDataBuilder withMarca(String marca) {
		this.marca = marca;
		return this;
	}

	public VehiculoDataBuilder withColor(String color) {
		this.color = color;
		return this;
	}

	public VehiculoDataBuilder withTipo(String tipo) {
		this.tipo = tipo;
		return this;
	}

	public VehiculoDataBuilder withCilindraje(double cilindraje) {
		this.cilindraje = cilindraje;
		return this;
	}

	public Vehiculo build() {
		return new Vehiculo(this.tipo, this.placa, this.cilindraje, this.marca, this.color);
	}
}
