package com.estacionamientoceiba.estacionamientoceiba.dominio.servicio;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.estacionamientoceiba.estacionamientoceiba.aplicacion.comando.manejador.JsonRespuesta;
import com.estacionamientoceiba.estacionamientoceiba.aplicacion.comando.manejador.JsonRespuestaCrear;
import com.estacionamientoceiba.estacionamientoceiba.dominio.excepcion.ExcepcionGenerica;
import com.estacionamientoceiba.estacionamientoceiba.dominio.modelo.Alquiler;
import com.estacionamientoceiba.estacionamientoceiba.dominio.modelo.Vehiculo;
import com.estacionamientoceiba.estacionamientoceiba.dominio.repositorio.RepositorioAlquiler;

public class ServicioCrearAlquilerEstacionamiento {
	private static final String PERMANENCIA = "El vehiculo ya se encuentra dentro del parqueadero";
	private static final String NO_DISPONIBILIDAD = "No hay disponibilidad para el vehiculo que intenta ingresar";
	private RepositorioAlquiler repositorioAlquiler;

	public ServicioCrearAlquilerEstacionamiento(RepositorioAlquiler repositorioAlquiler) {
		this.repositorioAlquiler = repositorioAlquiler;
	}

	public JsonRespuestaCrear ejecutar(Vehiculo vehiculo) {
		verificarDisponibilidad(vehiculo.getTipo());
		verificarPermanencia(vehiculo);
		Alquiler alquiler = new Alquiler(new Vehiculo(vehiculo), obtenerHoraIngreso());
		return this.repositorioAlquiler.crear(alquiler);
	}

	private void verificarPermanencia(Vehiculo vehiculo) {
		if (repositorioAlquiler.verificarPermanencia(vehiculo.getPlaca()))
			throw new ExcepcionGenerica(PERMANENCIA);
	}

	private void verificarDisponibilidad(String tipo) {
		List<Alquiler> plazas = new ArrayList<>(repositorioAlquiler.listar());
		int count = 0;

		for (Alquiler alquiler : plazas) {
			if (alquiler.getVehiculo().getTipo().equalsIgnoreCase(tipo)) {
				count++;
			}
		}

		if (("Carro".equalsIgnoreCase(tipo) && count >= 20) || (("Moto".equalsIgnoreCase(tipo)) && count >= 10)) {
			throw new ExcepcionGenerica(NO_DISPONIBILIDAD);
		}
	}

	private Date obtenerHoraIngreso() {
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss dd-MM-yyyy");
		String strDate = sdf.format(calendar.getTime());

		SimpleDateFormat sp = new SimpleDateFormat("HH:mm:ss dd-MM-yyyy");
		Date date = new Date();
		try {
			date = sp.parse(strDate);
		} catch (ParseException e) {
		}
		return date;
	}
}
