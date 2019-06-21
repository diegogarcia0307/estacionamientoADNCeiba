package com.estacionamientoceiba.estacionamientoceiba.dominio.modelo;

import com.estacionamientoceiba.estacionamientoceiba.dominio.modelo.validador.ValidadorVehiculo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
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

		this.tipo = vehiculo.getTipo();
		this.placa = vehiculo.getPlaca();
		this.marca = vehiculo.getMarca();
		this.color = vehiculo.getColor();
		this.cilindraje = vehiculo.getCilindraje();
	}

}
