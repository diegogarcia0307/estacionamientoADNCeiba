package com.estacionamientoceiba.estacionamientoceiba.infraestructura.controlador;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.estacionamientoceiba.estacionamientoceiba.aplicacion.comando.ComandoAlquiler;
import com.estacionamientoceiba.estacionamientoceiba.aplicacion.comando.respuestas.RespuestaCreacion;
import com.estacionamientoceiba.estacionamientoceiba.aplicacion.comando.respuestas.RespuestaListarAlquiler;
import com.estacionamientoceiba.estacionamientoceiba.aplicacion.comando.respuestas.RespuestaPagoSalida;
import com.estacionamientoceiba.estacionamientoceiba.aplicacion.manejador.ManejadorCrearAlquiler;
import com.estacionamientoceiba.estacionamientoceiba.aplicacion.manejador.ManejadorListarAlquileres;
import com.estacionamientoceiba.estacionamientoceiba.aplicacion.manejador.ManejadorSalidaAlquiler;
import com.estacionamientoceiba.estacionamientoceiba.dominio.modelo.Alquiler;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(tags = { "Controlador alquiler" })
@RequestMapping("/apiv1/alquileres")
public class ControladorAlquiler {

	private final ManejadorCrearAlquiler manejadorCrear;
	private final ManejadorListarAlquileres manejadorListarAlquiler;
	private final ManejadorSalidaAlquiler manejadorSalida;

	@Autowired
	public ControladorAlquiler(ManejadorCrearAlquiler manejadorCrear,
			ManejadorListarAlquileres manejadorListar,
			ManejadorSalidaAlquiler manejadorSalida) {
		this.manejadorCrear = manejadorCrear;
		this.manejadorListarAlquiler = manejadorListar;
		this.manejadorSalida = manejadorSalida;
	}

	@ApiOperation("crear")
	@PostMapping
	public RespuestaCreacion crear(@RequestBody ComandoAlquiler comandoIngresado) {
		return this.manejadorCrear.ejecutar(comandoIngresado);
	}

	@ApiOperation("salida")
	@PutMapping("/{placa}")
	public RespuestaPagoSalida salida(@PathVariable String placa) {
		return manejadorSalida.ejecutar(placa);
	}

	@ApiOperation("listarVehiculo")
	@GetMapping("/listar/vehiculo")
	public Collection<RespuestaListarAlquiler> listarVehiculo() {
		// this.manejadorListar.ejecutar();
		return null;
	}

	@ApiOperation("listaralquiler")
	@GetMapping("/listar/alquiler")
	public Collection<RespuestaListarAlquiler> listarAlquiler() {
		return this.manejadorListarAlquiler.ejecutar();
	}

	@ApiOperation("busqueda")
	@GetMapping("buscar/alquiler/{placa}")
	public RespuestaListarAlquiler buscar(@PathVariable String placa) {
		return this.manejadorListarAlquiler.buscarAlquiler(placa);
	}
}
