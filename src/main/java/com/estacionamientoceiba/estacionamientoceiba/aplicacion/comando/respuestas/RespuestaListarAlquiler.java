package com.estacionamientoceiba.estacionamientoceiba.aplicacion.comando.respuestas;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import com.estacionamientoceiba.estacionamientoceiba.dominio.modelo.Alquiler;
import com.estacionamientoceiba.estacionamientoceiba.dominio.modelo.Vehiculo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RespuestaListarAlquiler {
	private String placa;
	private Date fechaIngreso;
	private Date fechaSalida;
	private String puesto;
	private double pago;

	public Collection<RespuestaListarAlquiler> crearListaRespuesta(Collection<Alquiler> alquileres,
			Collection<Vehiculo> vehiculos) {

		ArrayList<RespuestaListarAlquiler> listado = new ArrayList();

		for (Alquiler alquiler : alquileres) {

		}
		return listado;
	}

	public RespuestaListarAlquiler crearRespuesta(Alquiler alquiler, Vehiculo vehiculo) {
		return this;
	}
}