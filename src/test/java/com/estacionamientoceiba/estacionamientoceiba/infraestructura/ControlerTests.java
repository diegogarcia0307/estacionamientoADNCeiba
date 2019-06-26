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
import org.springframework.web.util.NestedServletException;

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

	private final String URL = "/apiv1/alquileres/";
	private final String placa = "DDDE333";
	private final String carro = "CARRO";

	@Test
	public void aListarVacioTest() throws Exception {

		final ResultActions result = mvc.perform(get("/apiv1/alquileres").accept(MimeTypeUtils.APPLICATION_JSON_VALUE));

		result.andExpect(status().isOk());

	}

	@Test
	public void bRegistroVehiculoTest() throws Exception {
		JSONObject vehiculo = new JSONObject();
		JSONObject aux = new JSONObject();

		aux.put("placa", placa);
		aux.put("marca", "NISSAN");
		aux.put("color", "RED");
		aux.put("tipo", carro);
		aux.put("cilindraje", 0);
		vehiculo.put("vehiculo", aux);

		mvc.perform(MockMvcRequestBuilders.post(URL).content(vehiculo.toString())
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.estadoOperacion").exists());

	}

	@Test
	public void cListarTest() throws Exception {

		mvc.perform(MockMvcRequestBuilders.get(URL).accept(MediaType.APPLICATION_JSON)).andDo(print())
				.andExpect(status().isOk()).andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(1));

	}

	@Test
	public void dBuscarVehiculoTest() throws Exception {

		mvc.perform(MockMvcRequestBuilders.get(URL + "{placa}", placa).accept(MediaType.APPLICATION_JSON))
				.andDo(print()).andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.placa").value(placa));

	}

	@Test
	public void fDarSalidaVehiculoTest() throws Exception {
		mvc.perform(MockMvcRequestBuilders.put(URL + "{placa}", placa).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andExpect(MockMvcResultMatchers.jsonPath("$.pago").exists());
	}

	@Test(expected = NestedServletException.class)
	public void eIngresarVehiculoExistente() throws Exception {
		JSONObject vehiculo = new JSONObject();
		JSONObject vehiculoAux = new JSONObject();

		vehiculoAux.put("placa", placa);
		vehiculoAux.put("marca", "HONDA");
		vehiculoAux.put("color", "GREEN");
		vehiculoAux.put("tipo", "MOTO");
		vehiculoAux.put("cilindraje", 600);
		vehiculo.put("vehiculo", vehiculoAux);

		mvc.perform(MockMvcRequestBuilders.post(URL).content(vehiculo.toString())
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.jsonPath("$.estadoOperacion").doesNotExist());
	}

}
