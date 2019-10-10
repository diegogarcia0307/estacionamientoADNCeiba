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
import com.estacionamientoceiba.estacionamientoceiba.aplicacion.comando.respuestas.RespuestaListarVehiculo;
import com.estacionamientoceiba.estacionamientoceiba.aplicacion.comando.respuestas.RespuestaPagoSalida;
import com.estacionamientoceiba.estacionamientoceiba.aplicacion.manejador.ManejadorCrearAlquiler;
import com.estacionamientoceiba.estacionamientoceiba.aplicacion.manejador.ManejadorListarAlquileres;
import com.estacionamientoceiba.estacionamientoceiba.aplicacion.manejador.ManejadorListarVehiculo;
import com.estacionamientoceiba.estacionamientoceiba.aplicacion.manejador.ManejadorSalidaAlquiler;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(tags = { "Controlador alquiler" })
@RequestMapping("/apiv1/alquileres")
public class ControladorAlquiler {

	private final ManejadorCrearAlquiler manejadorCrear;
	private final ManejadorListarAlquileres manejadorListarAlquiler;
	private final ManejadorSalidaAlquiler manejadorSalida;
	private final ManejadorListarVehiculo manejadorListarVehiculo;

	@Autowired
	public ControladorAlquiler(ManejadorCrearAlquiler manejadorCrear, ManejadorListarAlquileres manejadorListarAlquiler,
			ManejadorSalidaAlquiler manejadorSalida,
			com.estacionamientoceiba.estacionamientoceiba.aplicacion.manejador.ManejadorListarVehiculo manejadorListarVehiculo) {
		this.manejadorCrear = manejadorCrear;
		this.manejadorListarAlquiler = manejadorListarAlquiler;
		this.manejadorSalida = manejadorSalida;
		this.manejadorListarVehiculo = manejadorListarVehiculo;
	}

	@ApiOperation("crear alquiler")
	@PostMapping
	public RespuestaCreacion crear(@RequestBody ComandoAlquiler comandoAlquiler) {
		return this.manejadorCrear.ejecutar(comandoAlquiler);
	}

	@ApiOperation("salida de vehiculo")
	@PutMapping("/{placa}")
	public RespuestaPagoSalida salida(@PathVariable String placa) {
		return manejadorSalida.ejecutar(placa);
	}

	@ApiOperation("busqueda de alquiler")
	@GetMapping("/buscar/alquiler/{placa}")
	public RespuestaListarAlquiler buscarAlquiler(@PathVariable String placa) {
		return this.manejadorListarAlquiler.buscarAlquiler(placa);
	}

	@ApiOperation("listar alquileres en uso")
	@GetMapping("/listar/alquiler/actuales")
	public Collection<RespuestaListarAlquiler> listarAlquileresEnUso() {
		return this.manejadorListarAlquiler.listarAlquileresEnUso();
	}

	@ApiOperation("listar todos los alquileres")
	@GetMapping("/listar/alquiler/todos")
	public Collection<RespuestaListarAlquiler> listarTodosLosAlquileres() {
		return this.manejadorListarAlquiler.listarTodosLosAlquileres();
	}

	@ApiOperation("busqueda de vehiculo")
	@GetMapping("/buscar/vehiculo/{placa}")
	public RespuestaListarVehiculo buscarVehiculo(@PathVariable String placa) {
		return this.manejadorListarVehiculo.buscarVehiculo(placa);
	}

	@ApiOperation("listar vehiculos en parqueadero")
	@GetMapping("/listar/vehiculo/actuales")
	public Collection<RespuestaListarVehiculo> listarVehiculosEnParqueadero() {
		return this.manejadorListarVehiculo.listarVehiculosEnParqueadero();
	}

	@ApiOperation("listar todos los vehiculos")
	@GetMapping("/listar/vehiculo/todos")
	public Collection<RespuestaListarVehiculo> listarTodosLosVehiculos() {
		return this.manejadorListarVehiculo.listarTodosLosVehiculos();
	}
}
