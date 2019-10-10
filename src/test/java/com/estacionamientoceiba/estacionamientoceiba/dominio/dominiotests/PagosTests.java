package com.estacionamientoceiba.estacionamientoceiba.dominio.dominiotests;

import static org.junit.Assert.assertEquals;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;

import com.estacionamientoceiba.estacionamientoceiba.dominio.databuilder.VehiculoDataBuilder;
import com.estacionamientoceiba.estacionamientoceiba.dominio.modelo.Vehiculo;
import com.estacionamientoceiba.estacionamientoceiba.dominio.modelo.validador.ValidadorAlquiler;

public class PagosTests {

	private final String FORMATO_FECHA = "HH:mm:ss dd-MM-yyyy";
	private final int CARRO = 1;
	private final int MOTO = 2;

	@Test
	public void calcularPagoMotoCilindrajeMayorTest() throws ParseException {
		// Arrange
		VehiculoDataBuilder buildVehiculo = new VehiculoDataBuilder();
		Vehiculo vehiculo = buildVehiculo.withTipo(MOTO).withCilindraje(600).withColor("RED").withMarca("BAJAJ")
				.withPlaca("WEARV23").buildVehiculo();

		SimpleDateFormat sdf = new SimpleDateFormat(FORMATO_FECHA);
		Date fechaIngreso = sdf.parse("8:00:00 23-06-2019");
		Date fechaSalida = sdf.parse("18:00:00 23-06-2019");

		// Act
		double esperado = 6000;
		double actual = ValidadorAlquiler.calcularPagoMotos(vehiculo.getCilindraje(), fechaIngreso, fechaSalida);

		// Assert
		assertEquals(actual, esperado, 0);
	}

	@Test
	public void calcularPagoMotoCilindrajeMenorTest() throws ParseException {
		// Arrange
		VehiculoDataBuilder buildVehiculo = new VehiculoDataBuilder();
		Vehiculo vehiculo = buildVehiculo.withTipo(MOTO).withCilindraje(120).withColor("RED").withMarca("SUZUKY")
				.withPlaca("8BHB4").buildVehiculo();

		SimpleDateFormat sdf = new SimpleDateFormat(FORMATO_FECHA);
		Date fechaIngreso = sdf.parse("5:00:00 23-06-2019");
		Date fechaSalida = sdf.parse("15:00:00 23-06-2019");

		// Act
		double esperado = 4000;
		double actual = ValidadorAlquiler.calcularPagoMotos(vehiculo.getCilindraje(), fechaIngreso, fechaSalida);

		// Assert

		assertEquals(actual, esperado, 0.0);
	}

	@Test
	public void calcularPagoHorasMotoCilindrajeMenorTest() throws ParseException {
		// Arrange
		VehiculoDataBuilder buildVehiculo = new VehiculoDataBuilder();
		Vehiculo vehiculo = buildVehiculo.withTipo(MOTO).withCilindraje(120).withColor("RED").withMarca("SUZUKY")
				.withPlaca("BH789S").buildVehiculo();

		SimpleDateFormat sdf = new SimpleDateFormat(FORMATO_FECHA);
		Date fechaIngreso = sdf.parse("5:00:00 23-06-2019");
		Date fechaSalida = sdf.parse("8:00:00 23-06-2019");

		// Act
		double esperado = 1500;
		double actual = ValidadorAlquiler.calcularPagoMotos(vehiculo.getCilindraje(), fechaIngreso, fechaSalida);

		// Assert

		assertEquals(actual, esperado, 0.0);
	}

	@Test
	public void calcularPagoCarrosTest() throws ParseException {
		// Arrange
		SimpleDateFormat sdf = new SimpleDateFormat(FORMATO_FECHA);
		Date fechaIngreso = sdf.parse("7:30:00 23-06-2019");
		Date fechaSalida = sdf.parse("10:30:00 24-06-2019");

		// Act
		double esperado = 11000;
		double actual = ValidadorAlquiler.calcularPagoCarros(fechaIngreso, fechaSalida);

		// Assert

		assertEquals(actual, esperado, 0.0);
	}

	@Test
	public void calcularPagoHorasCarrosTest() throws ParseException {
		// Arrange

		SimpleDateFormat sdf = new SimpleDateFormat(FORMATO_FECHA);
		Date fechaIngreso = sdf.parse("7:30:00 23-06-2019");
		Date fechaSalida = sdf.parse("10:30:00 23-06-2019");

		// Act
		double esperado = 3000;
		double actual = ValidadorAlquiler.calcularPagoCarros(fechaIngreso, fechaSalida);

		// Assert

		assertEquals(esperado, actual, 0.0);
	}

}
