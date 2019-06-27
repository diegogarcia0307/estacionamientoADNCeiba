package com.estacionamientoceiba.estacionamientoceiba.infraestructura;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.estacionamientoceiba.estacionamientoceiba.dominio.modelo.Alquiler;
import com.estacionamientoceiba.estacionamientoceiba.dominio.modelo.Vehiculo;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class BuscarTests {

	@Autowired
	private MockMvc mvc;

	private final String URL = "/apiv1/alquileres/";
	private final String PLACACARRO = "4RATT3";
	private final ObjectMapper objetoJSON = new ObjectMapper();

	@Test
	public void fBuscarCarroTest() throws Exception {

		int carro = 1;

		Alquiler alquiler = new Alquiler(new Vehiculo(carro, PLACACARRO, 1500, "KYA", "MARRON"));

		mvc.perform(MockMvcRequestBuilders.post(URL).content(objetoJSON.writeValueAsString(alquiler))
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.estadoOperacion").exists());

		mvc.perform(MockMvcRequestBuilders.get(URL + "{placa}", PLACACARRO).accept(MediaType.APPLICATION_JSON))
				.andDo(print()).andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.placa").value(PLACACARRO));

	}
}
