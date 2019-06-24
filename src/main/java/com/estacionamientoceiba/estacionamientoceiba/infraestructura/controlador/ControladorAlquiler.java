package com.estacionamientoceiba.estacionamientoceiba.infraestructura.controlador;

import java.util.Collection;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.estacionamientoceiba.estacionamientoceiba.aplicacion.comando.ComandoAlquiler;
import com.estacionamientoceiba.estacionamientoceiba.aplicacion.comando.manejador.JsonRespuesta;
import com.estacionamientoceiba.estacionamientoceiba.aplicacion.comando.manejador.ManejadorCrearAlquilerEstacionamiento;
import com.estacionamientoceiba.estacionamientoceiba.aplicacion.comando.manejador.ManejadorListarAlquileresEstacionamiento;
import com.estacionamientoceiba.estacionamientoceiba.aplicacion.comando.manejador.ManejadorSalidaAlquilerEstacionamiento;
import com.estacionamientoceiba.estacionamientoceiba.dominio.modelo.Alquiler;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(tags = { "Controlador alquiler" })
public class ControladorAlquiler {

	private final ManejadorCrearAlquilerEstacionamiento manejadorCrear;
	private final ManejadorListarAlquileresEstacionamiento manejadorListar;
	private final ManejadorSalidaAlquilerEstacionamiento manejadorSalida;

	public ControladorAlquiler(ManejadorCrearAlquilerEstacionamiento manejadorCrear,
			ManejadorListarAlquileresEstacionamiento manejadorListar,
			ManejadorSalidaAlquilerEstacionamiento manejadorSalida) {
		this.manejadorCrear = manejadorCrear;
		this.manejadorListar = manejadorListar;
		this.manejadorSalida = manejadorSalida;
	}

	@ApiOperation("listar")
	@GetMapping("/alquiler/listar")
	public Collection<Alquiler> listar() {
		return this.manejadorListar.ejecutar();
	}

	@ApiOperation("crearAlquiler")
	@PostMapping("/alquiler/crear")
	public boolean crearAlquiler(@RequestBody ComandoAlquiler comandoIngresado) {
		return this.manejadorCrear.ejecutar(comandoIngresado);
	}

	@ApiOperation("salidaAlquiler")
	@GetMapping("/alquiler/salida/{placa}")
	public double salidaAlquiler(@PathVariable String placa) {
		return this.manejadorSalida.salidaAlquiler(placa);
	}

	@ApiOperation("busquedaAlquiler")
	@GetMapping("/alquiler/busqueda/{placa}")
	public JsonRespuesta buscarAlquiler(@PathVariable String placa) {
		Alquiler alquiler = this.manejadorListar.buscarAlquiler(placa);
		return new JsonRespuesta(alquiler.getVehiculo().getPlaca(), alquiler.getVehiculo().getTipo(),
				alquiler.getFechaIngreso());

	}
}
