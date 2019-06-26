package com.estacionamientoceiba.estacionamientoceiba.infraestructura;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
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
	private final String PLACACARRO = "DDDE333";
	private final String PLACAMOTOMENOR = "23DRSG";
	private final String PLACAMOTOMAYOR = "SW342D";
	private final String CARRO = "CARRO";
	private final String MOTO = "MOTO";

	@Test
	public void aListarVacioTest() throws Exception {

		final ResultActions result = mvc.perform(get("/apiv1/alquileres").accept(MimeTypeUtils.APPLICATION_JSON_VALUE));

		final int expectedSize = 0;
		result.andExpect(status().isOk());
		result.andExpect(jsonPath("$.length()").value(expectedSize));

	}

	@Test
	public void bRegistroCarroTest() throws Exception {
		JSONObject vehiculo = new JSONObject();
		JSONObject aux = new JSONObject();

		aux.put("placa", PLACACARRO);
		aux.put("marca", "NISSAN");
		aux.put("color", "RED");
		aux.put("tipo", CARRO);
		aux.put("cilindraje", 0);
		vehiculo.put("vehiculo", aux);

		mvc.perform(MockMvcRequestBuilders.post(URL).content(vehiculo.toString())
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.estadoOperacion").exists());

	}

	@Test
	public void cRegistroMotoCilindrajeMenorTest() throws Exception {
		JSONObject vehiculo = new JSONObject();
		JSONObject aux = new JSONObject();

		aux.put("placa", PLACAMOTOMENOR);
		aux.put("tipo", MOTO);
		aux.put("cilindraje", 120);
		vehiculo.put("vehiculo", aux);

		mvc.perform(MockMvcRequestBuilders.post(URL).content(vehiculo.toString())
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.estadoOperacion").exists());

	}

	@Test
	public void dRegistroMotoCilindrajeMayorTest() throws Exception {
		JSONObject vehiculo = new JSONObject();
		JSONObject aux = new JSONObject();

		aux.put("placa", PLACAMOTOMAYOR);
		aux.put("tipo", MOTO);
		aux.put("cilindraje", 700);
		vehiculo.put("vehiculo", aux);

		mvc.perform(MockMvcRequestBuilders.post(URL).content(vehiculo.toString())
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
		JSONObject vehiculo = new JSONObject();
		JSONObject vehiculoAux = new JSONObject();

		vehiculoAux.put("placa", PLACACARRO);
		vehiculoAux.put("marca", "HONDA");
		vehiculoAux.put("color", "GREEN");
		vehiculoAux.put("tipo", "MOTO");
		vehiculoAux.put("cilindraje", 600);
		vehiculo.put("vehiculo", vehiculoAux);

		mvc.perform(MockMvcRequestBuilders.post(URL).content(vehiculo.toString())
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
