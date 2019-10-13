package com.estacionamientoceiba.estacionamientoceiba.infraestructura;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.util.NestedServletException;

import com.estacionamientoceiba.estacionamientoceiba.aplicacion.comando.ComandoAlquiler;
import com.estacionamientoceiba.estacionamientoceiba.infraestructura.databuilder.ComandoAlquilerDataBuilder;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class RegistrosTests {

	@Autowired
	private MockMvc mvc;

	private static final String URL = "/apiv1/alquileres/";
	private static final String PLACACARRO = "DDDE3u33";
	private static final String PLACAMOTOMENOR = "2u3DRSG";
	private static final String PLACAMOTOMAYOR = "SuW342D";
	private static final int CARRO = 1;
	private static final int MOTO = 2;
	private static final String ESTADO_DE_OPERACION = "$.estadoOperacion";

	private ObjectMapper objetoAlquiler = new ObjectMapper();

	private ComandoAlquiler comandoAlquiler;

	@Test
	public void registroCarroTest() throws Exception {

		comandoAlquiler = new ComandoAlquilerDataBuilder().conTipo(CARRO).conPlaca(PLACACARRO).conMarca("HYUNDAY")
				.construir();

		mvc.perform(MockMvcRequestBuilders.post(URL).content(objetoAlquiler.writeValueAsString(comandoAlquiler))
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath(ESTADO_DE_OPERACION).exists());

	}

	@Test
	public void registroMotoCilindrajeMenorTest() throws Exception {

		comandoAlquiler = new ComandoAlquilerDataBuilder().conTipo(MOTO).conPlaca(PLACAMOTOMENOR).conCilindraje(120)
				.construir();

		mvc.perform(MockMvcRequestBuilders.post(URL).content(objetoAlquiler.writeValueAsString(comandoAlquiler))
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath(ESTADO_DE_OPERACION).exists());

	}

	@Test
	public void registroMotoCilindrajeMayorTest() throws Exception {

		comandoAlquiler = new ComandoAlquilerDataBuilder().conTipo(MOTO).conPlaca(PLACAMOTOMAYOR).conCilindraje(600)
				.construir();

		mvc.perform(MockMvcRequestBuilders.post(URL).content(objetoAlquiler.writeValueAsString(comandoAlquiler))
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath(ESTADO_DE_OPERACION).exists());

	}

	@Test(expected = NestedServletException.class)
	public void ingresarCarroExistente() throws Exception {

		comandoAlquiler = new ComandoAlquilerDataBuilder().conTipo(CARRO).conPlaca("otro").construir();

		mvc.perform(MockMvcRequestBuilders.post(URL).content(objetoAlquiler.writeValueAsString(comandoAlquiler))
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath(ESTADO_DE_OPERACION).exists());

		mvc.perform(MockMvcRequestBuilders.post(URL).content(objetoAlquiler.writeValueAsString(comandoAlquiler))
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON));

	}

}
