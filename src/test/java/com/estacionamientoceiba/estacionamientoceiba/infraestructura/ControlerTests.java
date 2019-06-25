package com.estacionamientoceiba.estacionamientoceiba.infraestructura;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.json.JSONObject;
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

import com.estacionamientoceiba.estacionamientoceiba.aplicacion.comando.manejador.ManejadorListarAlquileresEstacionamiento;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ControlerTests {

	@Autowired
	private MockMvc mvc;

	@Mock
	ManejadorListarAlquileresEstacionamiento manejadorListarAlquileresEstacionamiento;

	@Test
	public void aListarVacioTest() throws Exception {

		final ResultActions result = mvc.perform(get("/apiv1/alquileres").accept(MimeTypeUtils.APPLICATION_JSON_VALUE));

		result.andExpect(status().isOk());
		/*
		 * final int expectedSize = 0;
		 * result.andExpect(jsonPath("$.length()").value(expectedSize));
		 */

	}

	@Test
	public void bRegistroVehiculoTest() throws Exception {
		JSONObject vehiculo = new JSONObject();
		JSONObject aux = new JSONObject();

		aux.put("placa", "DDDE333");
		aux.put("marca", "NISSAN");
		aux.put("color", "RED");
		aux.put("tipo", "CARRO");
		aux.put("cilindraje", 0);
		vehiculo.put("vehiculo", aux);

		JSONObject respuestaEsperada = new JSONObject();
		respuestaEsperada.put("placa", "DDDE333");
		respuestaEsperada.put("tipoVehiculo", "CARRO");
		respuestaEsperada.put("estadoOperacion", true);

		mvc.perform(MockMvcRequestBuilders.post("/apiv1/alquileres").content(vehiculo.toString())
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.estadoOperacion").exists());

	}

	@Test
	public void cListarTest() throws Exception {

		mvc.perform(MockMvcRequestBuilders.get("/apiv1/alquileres").accept(MediaType.APPLICATION_JSON)).andDo(print())
				.andExpect(status().isOk()).andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(1));

	}

	@Test
	public void dBuscarVehiculoTest() throws Exception {

		mvc.perform(
				MockMvcRequestBuilders.get("/apiv1/alquileres/{placa}", "DDDE333").accept(MediaType.APPLICATION_JSON))
				.andDo(print()).andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.placa").value("DDDE333"));

	}

}
