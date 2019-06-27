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

import com.estacionamientoceiba.estacionamientoceiba.dominio.modelo.Alquiler;
import com.estacionamientoceiba.estacionamientoceiba.dominio.modelo.Vehiculo;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class RegistrosTests {

	@Autowired
	private MockMvc mvc;

	private final String URL = "/apiv1/alquileres/";
	private final String PLACACARRO = "DDDE333";
	private final String PLACAMOTOMENOR = "23DRSG";
	private final String PLACAMOTOMAYOR = "SW342D";
	private final int CARRO = 1;
	private final int MOTO = 2;
	private final ObjectMapper objetoAlquiler = new ObjectMapper();

	@Test
	public void registroCarroTest() throws Exception {

		Alquiler alquiler = new Alquiler(new Vehiculo(CARRO, PLACACARRO, 2000, "RENAULT", "VERDE"));

		mvc.perform(MockMvcRequestBuilders.post(URL).content(objetoAlquiler.writeValueAsString(alquiler))
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.estadoOperacion").exists());

	}

	@Test
	public void registroMotoCilindrajeMenorTest() throws Exception {

		Alquiler alquiler = new Alquiler(new Vehiculo(MOTO, PLACAMOTOMENOR, 120, "KAWAZAKY", "AMARILLO"));

		mvc.perform(MockMvcRequestBuilders.post(URL).content(objetoAlquiler.writeValueAsString(alquiler))
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.estadoOperacion").exists());

	}

	@Test
	public void registroMotoCilindrajeMayorTest() throws Exception {
		Alquiler alquiler = new Alquiler(new Vehiculo(MOTO, PLACAMOTOMAYOR, 600, "AUTECO", "ROJO"));

		mvc.perform(MockMvcRequestBuilders.post(URL).content(objetoAlquiler.writeValueAsString(alquiler))
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.estadoOperacion").exists());

	}

	@Test(expected = NestedServletException.class)
	public void ingresarCarroExistente() throws Exception {

		Alquiler alquiler = new Alquiler(new Vehiculo(CARRO, "EXISTE", 2000, "KYA", "MARRON"));

		mvc.perform(MockMvcRequestBuilders.post(URL).content(objetoAlquiler.writeValueAsString(alquiler))
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.estadoOperacion").exists());

		mvc.perform(MockMvcRequestBuilders.post(URL).content(objetoAlquiler.writeValueAsString(alquiler))
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON));

	}

}
