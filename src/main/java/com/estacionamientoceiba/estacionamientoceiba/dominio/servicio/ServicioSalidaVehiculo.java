package com.estacionamientoceiba.estacionamientoceiba.dominio.servicio;

import java.util.Date;

import com.estacionamientoceiba.estacionamientoceiba.aplicacion.comando.manejador.respuestas.RespuestaPagoSalida;
import com.estacionamientoceiba.estacionamientoceiba.dominio.excepcion.ExcepcionGenerica;
import com.estacionamientoceiba.estacionamientoceiba.dominio.modelo.Vehiculo;
import com.estacionamientoceiba.estacionamientoceiba.dominio.modelo.validador.ValidadorAlquiler;
import com.estacionamientoceiba.estacionamientoceiba.dominio.repositorio.RepositorioAlquiler;

public class ServicioSalidaVehiculo {

	private static final String NO_EXISTE = "El veh�culo ingresado no se encuentra en el parqueadero";

	private RepositorioAlquiler repositorioAlquiler;

	public ServicioSalidaVehiculo(RepositorioAlquiler repositorioAlquiler) {
		this.repositorioAlquiler = repositorioAlquiler;
	}

	public RespuestaPagoSalida salidaAlquiler(String placa) {
		// verificarExistencia(placa);
		RespuestaPagoSalida pago = new RespuestaPagoSalida(calcularPago(repositorioAlquiler.buscarVehiculo(placa),
				repositorioAlquiler.buscarAlquiler(placa).getFechaIngreso()));
		repositorioAlquiler.salidaVehiculo(placa);
		// repositorioAlquiler.eliminarPlaza(placa);
		return pago;
	}

	private void verificarExistencia(String placa) {
		if (!repositorioAlquiler.verificarPermanencia(placa))
			throw new ExcepcionGenerica(NO_EXISTE);
	}

	private double calcularPago(Vehiculo vehiculo, Date fechaIngreso) {
		if (vehiculo.getTipo() == 2) {
			return ValidadorAlquiler.calcularPagoMotos(vehiculo, fechaIngreso, new Date());
		} else {
			return ValidadorAlquiler.calcularPagoCarros(vehiculo, fechaIngreso, new Date());
		}
	}

}
