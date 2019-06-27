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

	private final String FORMATO_FECHA = "HH:mm:ss dd-MM-yyyy";
	private final int CARRO = 1;
	private final int MOTO = 2;

	@Test
	public void calcularPagoMotoCilindrajeMayorTest() throws ParseException {
		// Arrange
		VehiculoDataBuilder buildVehiculo = new VehiculoDataBuilder();
		Vehiculo vehiculo = buildVehiculo.withTipo(MOTO).withCilindraje(600).withColor("RED").withMarca("BAJAJ")
				.withPlaca("WEARV23").build();

		SimpleDateFormat sdf = new SimpleDateFormat(FORMATO_FECHA);
		Date fechaIngreso = sdf.parse("8:00:00 23-06-2019");
		Date fechaSalida = sdf.parse("18:00:00 23-06-2019");

		// Act
		double esperado = 6000;
		double actual = ValidadorAlquiler.calcularPagoMotos(vehiculo, fechaIngreso, fechaSalida);

		// Assert

		assertEquals(actual, esperado, 0.0);
	}

	@Test
	public void calcularPagoMotoCilindrajeMenorTest() throws ParseException {
		// Arrange
		VehiculoDataBuilder buildVehiculo = new VehiculoDataBuilder();
		Vehiculo vehiculo = buildVehiculo.withTipo(MOTO).withCilindraje(120).withColor("RED").withMarca("SUZUKY")
				.withPlaca("8BHB4").build();

		SimpleDateFormat sdf = new SimpleDateFormat(FORMATO_FECHA);
		Date fechaIngreso = sdf.parse("5:00:00 23-06-2019");
		Date fechaSalida = sdf.parse("15:00:00 23-06-2019");

		// Act
		double esperado = 4000;
		double actual = ValidadorAlquiler.calcularPagoMotos(vehiculo, fechaIngreso, fechaSalida);

		// Assert

		assertEquals(actual, esperado, 0.0);
	}

	@Test
	public void calcularPagoHorasMotoCilindrajeMenorTest() throws ParseException {
		// Arrange
		VehiculoDataBuilder buildVehiculo = new VehiculoDataBuilder();
		Vehiculo vehiculo = buildVehiculo.withTipo(MOTO).withCilindraje(120).withColor("RED").withMarca("SUZUKY")
				.withPlaca("BH789S").build();

		SimpleDateFormat sdf = new SimpleDateFormat(FORMATO_FECHA);
		Date fechaIngreso = sdf.parse("5:00:00 23-06-2019");
		Date fechaSalida = sdf.parse("8:00:00 23-06-2019");

		// Act
		double esperado = 1500;
		double actual = ValidadorAlquiler.calcularPagoMotos(vehiculo, fechaIngreso, fechaSalida);

		// Assert

		assertEquals(actual, esperado, 0.0);
	}

	@Test
	public void calcularPagoCarrosTest() throws ParseException {
		// Arrange
		VehiculoDataBuilder buildVehiculo = new VehiculoDataBuilder();
		Vehiculo vehiculo = buildVehiculo.withTipo(CARRO).withCilindraje(120).withColor("RED").withMarca("TOYOTA")
				.withPlaca("1QSWA2").build();

		SimpleDateFormat sdf = new SimpleDateFormat(FORMATO_FECHA);
		Date fechaIngreso = sdf.parse("7:30:00 23-06-2019");
		Date fechaSalida = sdf.parse("10:30:00 24-06-2019");

		// Act
		double esperado = 11000;
		double actual = ValidadorAlquiler.calcularPagoCarros(vehiculo, fechaIngreso, fechaSalida);

		// Assert

		assertEquals(actual, esperado, 0.0);
	}

	@Test
	public void calcularPagoHorasCarrosTest() throws ParseException {
		// Arrange
		VehiculoDataBuilder buildVehiculo = new VehiculoDataBuilder();
		Vehiculo vehiculo = buildVehiculo.withTipo(CARRO).withCilindraje(120).withColor("RED").withMarca("TOYOTA")
				.withPlaca("SRD36D").build();

		SimpleDateFormat sdf = new SimpleDateFormat(FORMATO_FECHA);
		Date fechaIngreso = sdf.parse("7:30:00 23-06-2019");
		Date fechaSalida = sdf.parse("10:30:00 23-06-2019");

		// Act
		double esperado = 3000;
		double actual = ValidadorAlquiler.calcularPagoCarros(vehiculo, fechaIngreso, fechaSalida);

		// Assert

		assertEquals(esperado, actual, 0.0);
	}

	@Test
	public void verificarPlacaPermitidaSoloLunesDomingosTest() throws ParseException {
		// Arrange
		SimpleDateFormat sdf = new SimpleDateFormat(FORMATO_FECHA);
		Date fecha = sdf.parse("12:02:02 23-06-2019");

		String esperado = "Puede ingresar";

		// Act
		String actual = ValidadorAlquiler.verificarPlaca(CARRO, "AR43S", fecha);

		// Assert
		assert (actual.equalsIgnoreCase(esperado));
	}

	@Test(expected = ExcepcionGenerica.class)
	public void verificarPlacaNOPermitidaTest() throws ParseException {
		// Arrange
		SimpleDateFormat sdf = new SimpleDateFormat(FORMATO_FECHA);
		Date fecha = sdf.parse("15:05:32 25-06-2019");

		// Act
		ValidadorAlquiler.verificarPlaca(CARRO, "AR43S", fecha);

	}

	@Test
	public void verificarCilindrajeMenorMotoTest() {
		// Arrange
		VehiculoDataBuilder buildVehiculo = new VehiculoDataBuilder();
		Vehiculo vehiculo = buildVehiculo.withTipo(MOTO).withCilindraje(150).withColor("GRAY").withMarca("HONDA")
				.withPlaca("SDQW67").build();

		ValidadorAlquiler validador = new ValidadorAlquiler();
		// Act and Assert
		assert (!validador.verificarCilindrajeMoto(vehiculo));
	}

	@Test
	public void verificarCilindrajeMayorMotoTest() {
		// Arrange
		VehiculoDataBuilder buildVehiculo = new VehiculoDataBuilder();
		Vehiculo vehiculo = buildVehiculo.withTipo(MOTO).withCilindraje(600).withColor("GRAY").withMarca("AUTECO")
				.withPlaca("DJNHR").build();

		ValidadorAlquiler validador = new ValidadorAlquiler();

		// Act and Assert
		assert (validador.verificarCilindrajeMoto(vehiculo));
	}
}
