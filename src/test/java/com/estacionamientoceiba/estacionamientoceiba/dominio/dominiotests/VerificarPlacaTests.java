package com.estacionamientoceiba.estacionamientoceiba.dominio.dominiotests;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import com.estacionamientoceiba.estacionamientoceiba.dominio.excepcion.ExcepcionGenerica;
import com.estacionamientoceiba.estacionamientoceiba.dominio.modelo.validador.ValidadorAlquiler;

@FixMethodOrder(MethodSorters.JVM)
public class VerificarPlacaTests {

	private final String FORMATO_FECHA = "HH:mm:ss dd-MM-yyyy";
	private final int CARRO = 1;

	@Test
	public void verificarPlacaPermitidaSoloLunesDomingosTest() throws ParseException {
		// Arrange
		SimpleDateFormat sdf = new SimpleDateFormat(FORMATO_FECHA);
		Date fecha = sdf.parse("12:02:02 23-06-2019");

		String esperado = "Puede ingresar";

		// Act
		String actual = ValidadorAlquiler.verificarAccesoPlaca(CARRO, "AR43S", fecha);

		// Assert
		assert (actual.equalsIgnoreCase(esperado));
	}

	@Test(expected = ExcepcionGenerica.class)
	public void verificarPlacaNOPermitidaTest() throws ParseException {
		// Arrange
		SimpleDateFormat sdf = new SimpleDateFormat(FORMATO_FECHA);
		Date fecha = sdf.parse("15:05:32 25-06-2019");

		// Act
		ValidadorAlquiler.verificarAccesoPlaca(CARRO, "AR43S", fecha);

	}

}
