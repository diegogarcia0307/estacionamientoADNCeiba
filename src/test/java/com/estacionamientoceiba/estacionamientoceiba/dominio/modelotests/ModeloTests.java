package com.estacionamientoceiba.estacionamientoceiba.dominio.modelotests;

import java.util.Date;

import org.junit.Test;

import com.estacionamientoceiba.estacionamientoceiba.dominio.databuilder.AlquilerDataBuilder;
import com.estacionamientoceiba.estacionamientoceiba.dominio.databuilder.VehiculoDataBuilder;
import com.estacionamientoceiba.estacionamientoceiba.dominio.exepcion.ExcepcionCilindrajeCorrecto;
import com.estacionamientoceiba.estacionamientoceiba.dominio.exepcion.ExcepcionPlacaObligatoria;
import com.estacionamientoceiba.estacionamientoceiba.dominio.exepcion.ExcepcionTipoVehiculo;
import com.estacionamientoceiba.estacionamientoceiba.dominio.exepcion.ExcepcionVehiculoObligatorio;

public class ModeloTests {

	@Test(expected = ExcepcionPlacaObligatoria.class)
	public void verificarPlacaNulaVehiculoTest() {
		// Arrange
		VehiculoDataBuilder buildVehiculo = new VehiculoDataBuilder();

		// Act - Assert
		buildVehiculo.withPlaca(null).withCilindraje(200).withColor("FUCSIA").withTipo("MOTO").build();
	}

	@Test(expected = ExcepcionPlacaObligatoria.class)
	public void verificarPlacaVaciaVehiculoTest() {
		// Arrange
		VehiculoDataBuilder buildVehiculo = new VehiculoDataBuilder();

		// Act - Assert
		buildVehiculo.withPlaca("").withCilindraje(200).withColor("YELLOW").withTipo("CARRO").build();
	}

	@Test(expected = ExcepcionCilindrajeCorrecto.class)
	public void verificarCilindrajeNegativoTest() {
		// Arrange
		VehiculoDataBuilder buildVehiculo = new VehiculoDataBuilder();

		// Act - Assert
		buildVehiculo.withCilindraje(-10).withColor("RED").withMarca("AKT").withTipo("CARRO").build();
	}

	@Test(expected = ExcepcionTipoVehiculo.class)
	public void verificarTipoVehiculodDistintoTest() {
		// Arrange
		VehiculoDataBuilder buildVehiculo = new VehiculoDataBuilder();

		// Act - Assert
		buildVehiculo.withCilindraje(-10).withColor("BLACK").withMarca("HONDA").withTipo("BICICLETA").build();
	}

	@Test(expected = ExcepcionTipoVehiculo.class)
	public void verificarTipoVehiculoNuloTest() {
		// Arrange
		VehiculoDataBuilder buildVehiculo = new VehiculoDataBuilder();

		// Act - Assert
		buildVehiculo.withCilindraje(-10).withColor("WHITE").withMarca("HONDA").withTipo(null).build();
	}

	@Test(expected = ExcepcionVehiculoObligatorio.class)
	public void verificarVehiculoNuloTest() {
		AlquilerDataBuilder buildAlquiler = new AlquilerDataBuilder();
		Date fechaIngreso = new Date();
		buildAlquiler.withVehiculo(null).withPago(11000).withPuesto("MOTO 1").withFechaIngreso(fechaIngreso)
				.withFechaSalida(fechaIngreso).build();
	}

}
