package com.estacionamientoceiba.estacionamientoceiba.dominio.servicio;

import java.util.Date;

import com.estacionamientoceiba.estacionamientoceiba.aplicacion.comando.manejador.respuestas.RespuestaCreacion;
import com.estacionamientoceiba.estacionamientoceiba.dominio.excepcion.ExcepcionGenerica;
import com.estacionamientoceiba.estacionamientoceiba.dominio.modelo.Vehiculo;
import com.estacionamientoceiba.estacionamientoceiba.dominio.modelo.validador.ValidadorAlquiler;
import com.estacionamientoceiba.estacionamientoceiba.dominio.repositorio.RepositorioAlquiler;

public class ServicioCrearAlquilerEstacionamiento {
	private static final String VEHICULO_DENTRO_DEL_PARQUEADERO = "El vehiculo ya se encuentra dentro del parqueadero";
	private static final String NO_HAY_DISPONIBILIDAD = "No hay disponibilidad para el vehiculo que intenta ingresar";
	private RepositorioAlquiler repositorioAlquiler;

	public ServicioCrearAlquilerEstacionamiento(RepositorioAlquiler repositorioAlquiler) {
		this.repositorioAlquiler = repositorioAlquiler;
	}

	public RespuestaCreacion ejecutar(Vehiculo vehiculoParam) {
		Vehiculo vehiculo = new Vehiculo(vehiculoParam);
		this.verificarAccesoPlaca(vehiculo.getTipo(), vehiculo.getPlaca(), new Date());
		this.verificarPermanencia(vehiculo.getPlaca());
		this.verificarDisponibilidad(vehiculo.getTipo());
		long idAlquiler = this.repositorioAlquiler.crearAlquiler(vehiculo);
		return new RespuestaCreacion(String.valueOf(idAlquiler), idAlquiler > 0);
	}

	private void verificarPermanencia(String placa) {
		if (repositorioAlquiler.comprobarPermanenciaVehiculo(placa))
			throw new ExcepcionGenerica(VEHICULO_DENTRO_DEL_PARQUEADERO);
	}

	private void verificarDisponibilidad(int tipo) {
		if (!repositorioAlquiler.verificarDisponibilidad(tipo)) {
			throw new ExcepcionGenerica(NO_HAY_DISPONIBILIDAD);
		}
	}

	private void verificarAccesoPlaca(int tipo, String placa, Date dia) {
		ValidadorAlquiler.verificarAccesoPlaca(tipo, placa, dia);
	}

}
