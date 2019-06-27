package com.estacionamientoceiba.estacionamientoceiba.infraestructura;

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
import org.springframework.web.util.NestedServletException;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class SalidasTests {

	@Autowired
	private MockMvc mvc;

	private final String URL = "/apiv1/alquileres/";
	private final String PLACACARRO = "DDDE333";
	private final String PLACAMOTOMENOR = "23DRSG";
	private final String PLACAMOTOMAYOR = "SW342D";

	@Test
	public void hDarSalidaCarroTest() throws Exception {

		mvc.perform(MockMvcRequestBuilders.put(URL + "{placa}", PLACACARRO).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andExpect(MockMvcResultMatchers.jsonPath("$.pago").exists());

	}

	@Test
	public void iDarSalidaMotoCilindrajeMenorTest() throws Exception {

		mvc.perform(MockMvcRequestBuilders.put(URL + "{placa}", PLACAMOTOMENOR).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andExpect(MockMvcResultMatchers.jsonPath("$.pago").exists());

	}

	@Test
	public void jDarSalidaMotoCilindrajeMayorTest() throws Exception {

		mvc.perform(MockMvcRequestBuilders.put(URL + "{placa}", PLACAMOTOMAYOR).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andExpect(MockMvcResultMatchers.jsonPath("$.pago").exists());

	}

	@Test(expected = NestedServletException.class)
	public void kDarSalidaVehiculoNoIngresadoTest() throws Exception {

		mvc.perform(MockMvcRequestBuilders.put(URL + "{placa}", "DEFECTO").accept(MediaType.APPLICATION_JSON));

	}
}
