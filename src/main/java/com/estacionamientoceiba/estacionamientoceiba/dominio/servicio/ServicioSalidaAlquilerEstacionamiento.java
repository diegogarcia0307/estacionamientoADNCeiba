package com.estacionamientoceiba.estacionamientoceiba.dominio.servicio;

import com.estacionamientoceiba.estacionamientoceiba.aplicacion.comando.manejador.RespuestaPagoSalida;
import com.estacionamientoceiba.estacionamientoceiba.dominio.excepcion.ExcepcionGenerica;
import com.estacionamientoceiba.estacionamientoceiba.dominio.modelo.Alquiler;
import com.estacionamientoceiba.estacionamientoceiba.dominio.modelo.validador.ValidadorAlquiler;
import com.estacionamientoceiba.estacionamientoceiba.dominio.repositorio.RepositorioAlquiler;

public class ServicioSalidaAlquilerEstacionamiento {

	private static final String NO_EXISTE = "El vehículo ingresado no se encuentra en el parqueadero";

	private RepositorioAlquiler repositorioAlquiler;

	public ServicioSalidaAlquilerEstacionamiento(RepositorioAlquiler repositorioAlquiler) {
		this.repositorioAlquiler = repositorioAlquiler;
	}

	public RespuestaPagoSalida salidaAlquiler(String placa) {
		verificarExistencia(placa);
		RespuestaPagoSalida pago = new RespuestaPagoSalida(calcularPago(repositorioAlquiler.salidaAlquiler(placa)));
		repositorioAlquiler.eliminarPlaza(placa);
		return pago;
	}

	private void verificarExistencia(String placa) {
		if (!repositorioAlquiler.verificarPermanencia(placa))
			throw new ExcepcionGenerica(NO_EXISTE);
	}

	private double calcularPago(Alquiler alquiler) {
		if (alquiler.getVehiculo().getTipo() == 2) {
			return ValidadorAlquiler.calcularPagoMotos(alquiler.getVehiculo(), alquiler.getFechaIngreso(),
					alquiler.getFechaSalida());
		} else {
			return ValidadorAlquiler.calcularPagoCarros(alquiler.getVehiculo(), alquiler.getFechaIngreso(),
					alquiler.getFechaSalida());
		}
	}

}
