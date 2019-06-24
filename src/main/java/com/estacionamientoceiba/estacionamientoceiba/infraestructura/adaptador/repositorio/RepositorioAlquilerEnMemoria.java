package com.estacionamientoceiba.estacionamientoceiba.infraestructura.adaptador.repositorio;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Repository;

import com.estacionamientoceiba.estacionamientoceiba.dominio.modelo.Alquiler;
import com.estacionamientoceiba.estacionamientoceiba.dominio.modelo.Vehiculo;
import com.estacionamientoceiba.estacionamientoceiba.dominio.repositorio.RepositorioAlquiler;

@Repository
public class RepositorioAlquilerEnMemoria implements RepositorioAlquiler {

	private static ConcurrentHashMap<String, Alquiler> alquileres;

	static {
		alquileres = new ConcurrentHashMap<>();
		alquileres.put(UUID.randomUUID().toString(),
				new Alquiler(new Vehiculo("Carro", "WEQ43S", 2000, "NISSAN", "RED"), new Date(), new Date(), "#1C", 0));
	}

	@Override
	public boolean crear(Alquiler alquiler) {
		alquileres.put(UUID.randomUUID().toString(), alquiler);
		return true;
	}

	@Override
	public boolean modificar(String placa) {
		for (Alquiler alquiler : obtenerAlquileres()) {

			if (placa.equalsIgnoreCase(alquiler.getVehiculo().getPlaca())) {

				Calendar calendar = Calendar.getInstance();
				SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss dd-MM-yyyy");
				String strDate = sdf.format(calendar.getTime());

				SimpleDateFormat sp = new SimpleDateFormat("HH:mm:ss dd-MM-yyyy");
				try {
					Date date = sp.parse(strDate);

					alquiler.setFechaSalida(date);

					return true;
				} catch (ParseException e) {
				}
			}
		}
		return false;
	}

	@Override
	public boolean verificarPermanencia(Alquiler alquiler) {
		return obtenerAlquileres().stream()
				.anyMatch(fila -> fila.getVehiculo().getPlaca().equalsIgnoreCase(alquiler.getVehiculo().getPlaca()));
	}

	@Override
	public Collection<Alquiler> listar() {
		return obtenerAlquileres();
	}

	private static Collection<Alquiler> obtenerAlquileres() {
		return alquileres.values();
	}
}
