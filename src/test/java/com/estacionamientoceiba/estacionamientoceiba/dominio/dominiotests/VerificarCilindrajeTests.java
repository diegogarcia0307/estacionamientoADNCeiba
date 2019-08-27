package com.estacionamientoceiba.estacionamientoceiba.dominio.dominiotests;

import org.junit.Test;

import com.estacionamientoceiba.estacionamientoceiba.dominio.databuilder.VehiculoDataBuilder;
import com.estacionamientoceiba.estacionamientoceiba.dominio.modelo.Vehiculo;
import com.estacionamientoceiba.estacionamientoceiba.dominio.modelo.validador.ValidadorAlquiler;

public class VerificarCilindrajeTests {

	private final int MOTO = 2;

	@Test
	public void verificarCilindrajeMenorMotoTest() {
		// Arrange
		VehiculoDataBuilder buildVehiculo = new VehiculoDataBuilder();
		Vehiculo vehiculo = buildVehiculo.withTipo(MOTO).withCilindraje(150).withColor("GRAY").withMarca("HONDA")
				.withPlaca("SDQW67").buildVehiculo();

		ValidadorAlquiler validador = new ValidadorAlquiler();
		// Act and Assert
		assert (!validador.verificarCilindrajeMoto(vehiculo));
	}

	@Test
	public void verificarCilindrajeMayorMotoTest() {
		// Arrange
		VehiculoDataBuilder buildVehiculo = new VehiculoDataBuilder();
		Vehiculo vehiculo = buildVehiculo.withTipo(MOTO).withCilindraje(600).withColor("GRAY").withMarca("AUTECO")
				.withPlaca("DJNHR").buildVehiculo();

		ValidadorAlquiler validador = new ValidadorAlquiler();

		// Act and Assert
		assert (validador.verificarCilindrajeMoto(vehiculo));
	}

}
