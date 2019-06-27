package com.estacionamientoceiba.estacionamientoceiba.infraestructura;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.util.NestedServletException;

import com.estacionamientoceiba.estacionamientoceiba.aplicacion.comando.manejador.ManejadorListarAlquileresEstacionamiento;
import com.estacionamientoceiba.estacionamientoceiba.dominio.modelo.Alquiler;
import com.estacionamientoceiba.estacionamientoceiba.dominio.modelo.Vehiculo;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ControlerTests {

	@Autowired
	private MockMvc mvc;

	@Mock
	ManejadorListarAlquileresEstacionamiento manejadorListarAlquileresEstacionamiento;

	private final String URL = "/apiv1/alquileres/";
	private final String PLACACARRO = "DDDE333";
	private final String PLACAMOTOMENOR = "23DRSG";
	private final String PLACAMOTOMAYOR = "SW342D";
	private final int CARRO = 1;
	private final int MOTO = 2;
	private final ObjectMapper objetoAlquiler = new ObjectMapper();

	@Test
	public void aListarVacioTest() throws Exception {

		final ResultActions result = mvc.perform(get("/apiv1/alquileres").accept(MimeTypeUtils.APPLICATION_JSON_VALUE));

		final int expectedSize = 0;
		result.andExpect(status().isOk());
		result.andExpect(jsonPath("$.length()").value(expectedSize));

	}

	@Test
	public void bRegistroCarroTest() throws Exception {

		Alquiler alquiler = new Alquiler(new Vehiculo(CARRO, PLACACARRO, 2000, "RENAULT", "VERDE"));

		mvc.perform(MockMvcRequestBuilders.post(URL).content(objetoAlquiler.writeValueAsString(alquiler))
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.estadoOperacion").exists());

	}

	@Test
	public void cRegistroMotoCilindrajeMenorTest() throws Exception {

		Alquiler alquiler = new Alquiler(new Vehiculo(MOTO, PLACAMOTOMENOR, 120, "KAWAZAKY", "AMARILLO"));

		mvc.perform(MockMvcRequestBuilders.post(URL).content(objetoAlquiler.writeValueAsString(alquiler))
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.estadoOperacion").exists());

	}

	@Test
	public void dRegistroMotoCilindrajeMayorTest() throws Exception {
		Alquiler alquiler = new Alquiler(new Vehiculo(MOTO, PLACAMOTOMAYOR, 600, "AUTECO", "ROJO"));

		mvc.perform(MockMvcRequestBuilders.post(URL).content(objetoAlquiler.writeValueAsString(alquiler))
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.estadoOperacion").exists());

	}

	@Test
	public void eListarTest() throws Exception {

		mvc.perform(MockMvcRequestBuilders.get(URL).accept(MediaType.APPLICATION_JSON)).andDo(print())
				.andExpect(status().isOk()).andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(3));

	}

	@Test
	public void fBuscarCarroTest() throws Exception {

		mvc.perform(MockMvcRequestBuilders.get(URL + "{placa}", PLACACARRO).accept(MediaType.APPLICATION_JSON))
				.andDo(print()).andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.placa").value(PLACACARRO));

	}

	@Test(expected = NestedServletException.class)
	public void gIngresarCarroExistente() throws Exception {

		Alquiler alquiler = new Alquiler(new Vehiculo(CARRO, PLACACARRO, 2000, "KYA", "MARRON"));

		mvc.perform(MockMvcRequestBuilders.post(URL).content(objetoAlquiler.writeValueAsString(alquiler))
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON));

	}

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
