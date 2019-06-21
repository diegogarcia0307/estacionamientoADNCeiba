package com.estacionamientoceiba.estacionamientoceiba.infraestructura.controlador;

import java.util.Collection;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.estacionamientoceiba.estacionamientoceiba.aplicacion.comando.ComandoAlquiler;
import com.estacionamientoceiba.estacionamientoceiba.aplicacion.comando.manejador.ManejadorCrearAlquilerEstacionamiento;
import com.estacionamientoceiba.estacionamientoceiba.aplicacion.comando.manejador.ManejadorListarAlquileresEstacionamiento;
import com.estacionamientoceiba.estacionamientoceiba.dominio.modelo.Alquiler;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/alquiler/registros")
@Api(tags = { "Controlador alquiler" })
public class ControladorAlquiler {

	private final ManejadorCrearAlquilerEstacionamiento manejadorCrear;
	private final ManejadorListarAlquileresEstacionamiento manejadorListar;

	public ControladorAlquiler(ManejadorCrearAlquilerEstacionamiento manejadorCrear, ManejadorListarAlquileresEstacionamiento manejadorListar) {
		this.manejadorCrear = manejadorCrear;
		this.manejadorListar = manejadorListar;
	}

	@RequestMapping(method = RequestMethod.GET)
	@ApiOperation("listar")
	public Collection<Alquiler> listar() {
		return this.manejadorListar.ejecutar();
	}

	@PostMapping
	@ApiOperation("crearAlquiler")
	public void crearAlquiler(@RequestBody ComandoAlquiler comandoIngresado) {
		this.manejadorCrear.ejecutar(comandoIngresado);
	}
}