package com.estacionamientoceiba.estacionamientoceiba.aplicacion.fabrica.respuestas;

import java.util.ArrayList;
import java.util.Collection;

import com.estacionamientoceiba.estacionamientoceiba.aplicacion.comando.respuestas.RespuestaListarVehiculo;
import com.estacionamientoceiba.estacionamientoceiba.dominio.modelo.Vehiculo;

public class FabricaRespuestaListarVehiculo {

	public RespuestaListarVehiculo construir(Vehiculo vehiculo) {
		return new RespuestaListarVehiculo(vehiculo.getIdVehiculo(), vehiculo.getPlaca(), vehiculo.getMarca(),
				vehiculo.getColor(), vehiculo.getCilindraje(), vehiculo.getTipo());
	}

	public Collection<RespuestaListarVehiculo> construirColeccion(Collection<Vehiculo> vehiculos) {
		ArrayList<RespuestaListarVehiculo> coleccion = new ArrayList<>();

		for (Vehiculo vehiculo : vehiculos)
			coleccion.add(construir(vehiculo));

		return coleccion;
	}

}
