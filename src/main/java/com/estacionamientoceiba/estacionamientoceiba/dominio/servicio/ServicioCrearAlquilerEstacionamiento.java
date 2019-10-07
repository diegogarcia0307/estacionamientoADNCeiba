package com.estacionamientoceiba.estacionamientoceiba.dominio.servicio;

import com.estacionamientoceiba.estacionamientoceiba.aplicacion.comando.manejador.respuestas.RespuestaCreacion;
import com.estacionamientoceiba.estacionamientoceiba.dominio.excepcion.ExcepcionGenerica;
import com.estacionamientoceiba.estacionamientoceiba.dominio.modelo.Vehiculo;
import com.estacionamientoceiba.estacionamientoceiba.dominio.repositorio.RepositorioAlquiler;

public class ServicioCrearAlquilerEstacionamiento {
	private static final String VEHICULO_DENTRO_DEL_PARQUEADERO = "El vehiculo ya se encuentra dentro del parqueadero";
	private static final String NO_HAY_DISPONIBILIDAD = "No hay disponibilidad para el vehiculo que intenta ingresar";
	private RepositorioAlquiler repositorioAlquiler;

	public ServicioCrearAlquilerEstacionamiento(RepositorioAlquiler repositorioAlquiler) {
		this.repositorioAlquiler = repositorioAlquiler;
	}

	public RespuestaCreacion ejecutar(Vehiculo vehiculo) {
		this.verificarPermanencia(vehiculo);
		this.verificarDisponibilidad(vehiculo.getTipo());
		return this.repositorioAlquiler.crearAlquiler(vehiculo);
	}

	private void verificarPermanencia(Vehiculo vehiculo) {
		if (repositorioAlquiler.comprobarPermanenciaVehiculo(vehiculo.getPlaca()))
			throw new ExcepcionGenerica(VEHICULO_DENTRO_DEL_PARQUEADERO);
	}

	private void verificarDisponibilidad(int tipo) {
		if (!repositorioAlquiler.verificarDisponibilidad(tipo)) {
			throw new ExcepcionGenerica(NO_HAY_DISPONIBILIDAD);
		}
	}

}
