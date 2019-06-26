package com.estacionamientoceiba.estacionamientoceiba.dominio.dominiotests;

import static org.junit.Assert.assertEquals;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import com.estacionamientoceiba.estacionamientoceiba.dominio.databuilder.VehiculoDataBuilder;
import com.estacionamientoceiba.estacionamientoceiba.dominio.excepcion.ExcepcionGenerica;
import com.estacionamientoceiba.estacionamientoceiba.dominio.modelo.Vehiculo;
import com.estacionamientoceiba.estacionamientoceiba.dominio.modelo.validador.ValidadorAlquiler;

@FixMethodOrder(MethodSorters.JVM)
public class DominioTests {

	@Test
	public void calcularPagoMotoCilindrajeMayorTest() throws ParseException {
		// Arrange
		ValidadorAlquiler validador = new ValidadorAlquiler();

		VehiculoDataBuilder buildVehiculo = new VehiculoDataBuilder();
		Vehiculo vehiculo = buildVehiculo.withTipo("Moto").withCilindraje(600).withColor("RED").withMarca("HONDA")
				.withPlaca("SRD36D").build();

		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss dd-MM-yyyy");
		Date fechaIngreso = sdf.parse("8:00:00 23-06-2019");
		Date fechaSalida = sdf.parse("18:00:00 23-06-2019");

		// Act
		double esperado = 6000;
		double actual = validador.calcularPagoMotos(vehiculo, fechaIngreso, fechaSalida);

		// Assert

		assertEquals(actual, esperado, 0.0);
	}

	@Test
	public void calcularPagoMotoCilindrajeMenorTest() throws ParseException {
		// Arrange
		ValidadorAlquiler validador = new ValidadorAlquiler();

		VehiculoDataBuilder buildVehiculo = new VehiculoDataBuilder();
		Vehiculo vehiculo = buildVehiculo.withTipo("Moto").withCilindraje(120).withColor("RED").withMarca("SUZUKY")
				.withPlaca("SRD36D").build();

		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss dd-MM-yyyy");
		Date fechaIngreso = sdf.parse("5:00:00 23-06-2019");
		Date fechaSalida = sdf.parse("15:00:00 23-06-2019");

		// Act
		double esperado = 4000;
		double actual = validador.calcularPagoMotos(vehiculo, fechaIngreso, fechaSalida);

		// Assert

		assertEquals(actual, esperado, 0.0);
	}

	@Test
	public void calcularPagoHorasMotoCilindrajeMenorTest() throws ParseException {
		// Arrange
		ValidadorAlquiler validador = new ValidadorAlquiler();

		VehiculoDataBuilder buildVehiculo = new VehiculoDataBuilder();
		Vehiculo vehiculo = buildVehiculo.withTipo("Moto").withCilindraje(120).withColor("RED").withMarca("SUZUKY")
				.withPlaca("SRD36D").build();

		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss dd-MM-yyyy");
		Date fechaIngreso = sdf.parse("5:00:00 23-06-2019");
		Date fechaSalida = sdf.parse("8:00:00 23-06-2019");

		// Act
		double esperado = 1500;
		double actual = validador.calcularPagoMotos(vehiculo, fechaIngreso, fechaSalida);

		// Assert

		assertEquals(actual, esperado, 0.0);
	}

	@Test
	public void calcularPagoCarrosTest() throws ParseException {
		// Arrange
		ValidadorAlquiler validador = new ValidadorAlquiler();

		VehiculoDataBuilder buildVehiculo = new VehiculoDataBuilder();
		Vehiculo vehiculo = buildVehiculo.withTipo("Carro").withCilindraje(120).withColor("RED").withMarca("TOYOTA")
				.withPlaca("SRD36D").build();

		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss dd-MM-yyyy");
		Date fechaIngreso = sdf.parse("7:30:00 23-06-2019");
		Date fechaSalida = sdf.parse("10:30:00 24-06-2019");

		// Act
		double esperado = 11000;
		double actual = validador.calcularPagoCarros(vehiculo, fechaIngreso, fechaSalida);

		// Assert

		assertEquals(actual, esperado, 0.0);
	}

	@Test
	public void calcularPagoHorasCarrosTest() throws ParseException {
		// Arrange
		ValidadorAlquiler validador = new ValidadorAlquiler();

		VehiculoDataBuilder buildVehiculo = new VehiculoDataBuilder();
		Vehiculo vehiculo = buildVehiculo.withTipo("Carro").withCilindraje(120).withColor("RED").withMarca("TOYOTA")
				.withPlaca("SRD36D").build();

		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss dd-MM-yyyy");
		Date fechaIngreso = sdf.parse("7:30:00 23-06-2019");
		Date fechaSalida = sdf.parse("10:30:00 23-06-2019");

		// Act
		double esperado = 3000;
		double actual = validador.calcularPagoCarros(vehiculo, fechaIngreso, fechaSalida);

		// Assert

		assertEquals(esperado, actual, 0.0);
	}

	@Test
	public void verificarPlacaPermitidaSoloLunesDomingosTest() throws ParseException {
		// Arrange
		ValidadorAlquiler validador = new ValidadorAlquiler();

		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		Date fecha = sdf.parse("23-06-2019");

		String esperado = "Puede ingresar";

		// Act
		String actual = validador.verificarPlaca("Carro", "AR43S", fecha);

		// Assert
		assert (actual.equalsIgnoreCase(esperado));
	}

	@Test(expected = ExcepcionGenerica.class)
	public void verificarPlacaNOPermitidaTest() throws ParseException {
		// Arrange
		ValidadorAlquiler validador = new ValidadorAlquiler();

		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		Date fecha = sdf.parse("25-06-2019");

		// Act
		String actual = validador.verificarPlaca("Carro", "AR43S", fecha);

	}

	@Test(expected = ExcepcionGenerica.class)
	public void verificarPlacaPermitiDaSiempreTest() throws ParseException {
		// Arrange
		ValidadorAlquiler validador = new ValidadorAlquiler();

		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		Date fecha = sdf.parse("25-06-2019");

		// Act
		String actual = validador.verificarPlaca("Carro", "B3ES4D", fecha);

	}

	@Test
	public void verificarCilindrajeMenorMotoTest() {
		// Arrange
		VehiculoDataBuilder buildVehiculo = new VehiculoDataBuilder();
		Vehiculo vehiculo = buildVehiculo.withTipo("MOTO").withCilindraje(150).withColor("GRAY").withMarca("HONDA")
				.withPlaca("DJNHR").build();

		ValidadorAlquiler validador = new ValidadorAlquiler();
		// Act and Assert
		assert (!validador.verificarCilindrajeMoto(vehiculo));
	}

	@Test
	public void verificarCilindrajeMayorMotoTest() {
		// Arrange
		VehiculoDataBuilder buildVehiculo = new VehiculoDataBuilder();
		Vehiculo vehiculo = buildVehiculo.withTipo("MOTO").withCilindraje(600).withColor("GRAY").withMarca("HONDA")
				.withPlaca("DJNHR").build();

		ValidadorAlquiler validador = new ValidadorAlquiler();

		// Act and Assert
		assert (validador.verificarCilindrajeMoto(vehiculo));
	}
}
