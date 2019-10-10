package com.estacionamientoceiba.estacionamientoceiba.aplicacion.fabrica.respuestas;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.stereotype.Component;

import com.estacionamientoceiba.estacionamientoceiba.aplicacion.comando.respuestas.RespuestaListarAlquiler;
import com.estacionamientoceiba.estacionamientoceiba.dominio.modelo.Alquiler;
import com.estacionamientoceiba.estacionamientoceiba.dominio.modelo.Vehiculo;

@Component
public class FabricaRespuestaListarAlquiler {

	public RespuestaListarAlquiler construir(Alquiler alquiler, Vehiculo vehiculo) {

		return new RespuestaListarAlquiler(vehiculo.getIdVehiculo(), vehiculo.getPlaca(), vehiculo.getTipo(),
				alquiler.getIdAlquiler(), alquiler.getFechaIngreso(), alquiler.getFechaSalida(), alquiler.getPuesto(),
				alquiler.getPago());

	}

	public Collection<RespuestaListarAlquiler> construirColeccion(Collection<Alquiler> alquileres,
			Collection<Vehiculo> vehiculos) {

		ArrayList<RespuestaListarAlquiler> listado = new ArrayList<>();

		for (Alquiler alquiler : alquileres)
			for (Vehiculo vehiculo : vehiculos)

				if (alquiler.getIdVehiculo() == vehiculo.getIdVehiculo())
					listado.add(this.construir(alquiler, vehiculo));

		return listado;

	}
}
