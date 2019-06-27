package com.estacionamientoceiba.estacionamientoceiba.dominio.modelotests;

import java.util.Date;

import org.junit.Test;

import com.estacionamientoceiba.estacionamientoceiba.dominio.databuilder.AlquilerDataBuilder;
import com.estacionamientoceiba.estacionamientoceiba.dominio.databuilder.VehiculoDataBuilder;
import com.estacionamientoceiba.estacionamientoceiba.dominio.excepcion.ExcepcionCilindrajeCorrecto;
import com.estacionamientoceiba.estacionamientoceiba.dominio.excepcion.ExcepcionPlacaObligatoria;
import com.estacionamientoceiba.estacionamientoceiba.dominio.excepcion.ExcepcionTipoVehiculo;
import com.estacionamientoceiba.estacionamientoceiba.dominio.excepcion.ExcepcionVehiculoObligatorio;

public class CreacionVehiculos {

	private final int CARRO = 1;
	private final int MOTO = 2;

	@Test(expected = ExcepcionPlacaObligatoria.class)
	public void verificarPlacaNulaVehiculoTest() {
		// Arrange
		VehiculoDataBuilder buildVehiculo = new VehiculoDataBuilder();

		// Act - Assert
		buildVehiculo.withPlaca(null).withCilindraje(200).withColor("FUCSIA").withTipo(MOTO).build();
	}

	@Test(expected = ExcepcionPlacaObligatoria.class)
	public void verificarPlacaVaciaVehiculoTest() {
		// Arrange
		VehiculoDataBuilder buildVehiculo = new VehiculoDataBuilder();

		// Act - Assert
		buildVehiculo.withPlaca("").withCilindraje(200).withColor("YELLOW").withTipo(CARRO).build();
	}

	@Test(expected = ExcepcionCilindrajeCorrecto.class)
	public void verificarCilindrajeNegativoTest() {
		// Arrange
		VehiculoDataBuilder buildVehiculo = new VehiculoDataBuilder();

		// Act - Assert
		buildVehiculo.withCilindraje(-10).withColor("RED").withMarca("AKT").withTipo(CARRO).build();
	}

	@Test(expected = ExcepcionTipoVehiculo.class)
	public void verificarTipoVehiculodDistintoTest() {
		// Arrange
		VehiculoDataBuilder buildVehiculo = new VehiculoDataBuilder();

		// Act - Assert
		buildVehiculo.withCilindraje(-10).withColor("BLACK").withMarca("HONDA").withTipo(3).build();
	}

	@Test(expected = ExcepcionTipoVehiculo.class)
	public void verificarTipoVehiculoNuloTest() {
		// Arrange
		VehiculoDataBuilder buildVehiculo = new VehiculoDataBuilder();

		// Act - Assert
		buildVehiculo.withCilindraje(-10).withColor("WHITE").withMarca("HONDA").withTipo(0).build();
	}

	@Test(expected = ExcepcionVehiculoObligatorio.class)
	public void verificarVehiculoNuloTest() {
		AlquilerDataBuilder buildAlquiler = new AlquilerDataBuilder();
		Date fechaIngreso = new Date();
		buildAlquiler.withVehiculo(null).withPago(11000).withPuesto("MOTO 1").withFechaIngreso(fechaIngreso)
				.withFechaSalida(fechaIngreso).build();
	}

}
