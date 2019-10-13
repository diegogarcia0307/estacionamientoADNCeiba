package com.estacionamientoceiba.estacionamientoceiba.infraestructura.databuilder;

import com.estacionamientoceiba.estacionamientoceiba.aplicacion.comando.ComandoAlquiler;

public class ComandoAlquilerDataBuilder {
	private String placa;
	private String marca;
	private String color;
	private double cilindraje;
	private int tipo;

	public ComandoAlquilerDataBuilder() {
		placa = marca = color = "<DEFAULT>";
		cilindraje = 0;
		tipo = 0;
	}

	public ComandoAlquilerDataBuilder conPlaca(String placa) {
		this.placa = placa;
		return this;
	}

	public ComandoAlquilerDataBuilder conMarca(String marca) {
		this.marca = marca;
		return this;
	}

	public ComandoAlquilerDataBuilder conColor(String color) {
		this.color = color;
		return this;
	}

	public ComandoAlquilerDataBuilder conCilindraje(double cilindraje) {
		this.cilindraje = cilindraje;
		return this;
	}

	public ComandoAlquilerDataBuilder conTipo(int tipo) {
		this.tipo = tipo;
		return this;
	}

	public ComandoAlquiler construir() {
		ComandoAlquiler comandoAlquiler = new ComandoAlquiler();
		comandoAlquiler.setCilindraje(cilindraje);
		comandoAlquiler.setColor(color);
		comandoAlquiler.setMarca(marca);
		comandoAlquiler.setPlaca(placa);
		comandoAlquiler.setTipo(tipo);
		return comandoAlquiler;
	}
}
