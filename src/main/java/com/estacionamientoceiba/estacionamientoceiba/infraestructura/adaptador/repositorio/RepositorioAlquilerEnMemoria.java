package com.estacionamientoceiba.estacionamientoceiba.infraestructura.adaptador.repositorio;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Repository;

import com.estacionamientoceiba.estacionamientoceiba.aplicacion.comando.manejador.JsonRespuestaCrear;
import com.estacionamientoceiba.estacionamientoceiba.dominio.modelo.Alquiler;
import com.estacionamientoceiba.estacionamientoceiba.dominio.repositorio.RepositorioAlquiler;

@Repository
public class RepositorioAlquilerEnMemoria implements RepositorioAlquiler {

	private static ConcurrentHashMap<String, Alquiler> alquileres;

	static {
		alquileres = new ConcurrentHashMap<>();
	}

	@Override
	public JsonRespuestaCrear crear(Alquiler alquiler) {
		alquileres.put(UUID.randomUUID().toString(), alquiler);
		return new JsonRespuestaCrear(alquiler.getVehiculo().getPlaca(), alquiler.getVehiculo().getTipo(), true);
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
	public Collection<Alquiler> listar() {
		return obtenerAlquileres();
	}

	private static Collection<Alquiler> obtenerAlquileres() {
		return alquileres.values();
	}

	@Override
	public Alquiler buscarAlquiler(String placa) {
		List<Alquiler> lista = new ArrayList<>(listar());
		Alquiler alquiler = new Alquiler();
		for (int i = 0; i < lista.size(); i++) {
			if (lista.get(i).getVehiculo().getPlaca().equalsIgnoreCase(placa)) {
				alquiler = lista.get(i);
			}
		}
		return alquiler;
	}

	@Override
	public void eliminarPlaza(String placa) {

		Alquiler alquiler = buscarAlquiler(placa);

		alquileres.values().remove(alquiler);

	}
}
