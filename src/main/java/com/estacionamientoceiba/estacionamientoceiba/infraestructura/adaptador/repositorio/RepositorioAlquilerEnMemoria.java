package com.estacionamientoceiba.estacionamientoceiba.infraestructura.adaptador.repositorio;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.estacionamientoceiba.estacionamientoceiba.aplicacion.comando.manejador.respuestas.RespuestaCreacion;
import com.estacionamientoceiba.estacionamientoceiba.dominio.modelo.Alquiler;
import com.estacionamientoceiba.estacionamientoceiba.dominio.repositorio.RepositorioAlquiler;

@Repository
public class RepositorioAlquilerEnMemoria implements RepositorioAlquiler {

	private static ConcurrentHashMap<String, Alquiler> alquileres;
	private static final Logger LOG = LoggerFactory.getLogger(RepositorioAlquilerEnMemoria.class);

	static {
		alquileres = new ConcurrentHashMap<>();
	}

	@Override
	public RespuestaCreacion crear(Alquiler alquiler) {
		alquileres.put(UUID.randomUUID().toString(), alquiler);
		RespuestaCreacion respuesta = new RespuestaCreacion(alquiler.getVehiculo().getPlaca(),
				alquiler.getVehiculo().getTipo(), true);
		System.out.println(respuesta.toString());
		return respuesta;
	}

	@Override
	public Alquiler salidaAlquiler(String placa) {

		Alquiler alquilerSalir = new Alquiler();

		for (Alquiler alquiler : obtenerAlquileres()) {

			if (placa.equalsIgnoreCase(alquiler.getVehiculo().getPlaca())) {

				Calendar calendar = Calendar.getInstance();
				SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss dd-MM-yyyy");
				String strDate = sdf.format(calendar.getTime());

				SimpleDateFormat sp = new SimpleDateFormat("HH:mm:ss dd-MM-yyyy");
				try {
					Date date = sp.parse(strDate);

					alquiler.setFechaSalida(date);

					alquilerSalir = alquiler;
				} catch (ParseException e) {
					LOG.error(e.getMessage(), e);
				}

			}
		}
		return alquilerSalir;
	}

	@Override
	public boolean verificarPermanencia(String placa) {
		return obtenerAlquileres().stream().anyMatch(fila -> fila.getVehiculo().getPlaca().equalsIgnoreCase(placa));
	}

	@Override
	public Collection<Alquiler> listarTodo() {
		return obtenerAlquileres();
	}

	private static Collection<Alquiler> obtenerAlquileres() {
		return alquileres.values();
	}

	@Override
	public Alquiler buscarAlquiler(String placa) {
		for (Alquiler alquiler : obtenerAlquileres()) {
			if (placa.equalsIgnoreCase(alquiler.getVehiculo().getPlaca()))
				return alquiler;
		}
		return new Alquiler();
	}

	@Override
	public void eliminarPlaza(String placa) {

		Alquiler alquiler = buscarAlquiler(placa);

		alquileres.values().remove(alquiler);

	}
}
