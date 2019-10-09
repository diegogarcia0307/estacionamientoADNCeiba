package com.estacionamientoceiba.estacionamientoceiba.aplicacion.comando.respuestas;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RespuestaCreacion {

	String idGenerado;
	boolean estadoOperacion;
	String mensaje;

	public RespuestaCreacion(RespuestaCreacion respuesta) {
		idGenerado = respuesta.getIdGenerado();
		estadoOperacion = respuesta.isEstadoOperacion();
		mensaje = respuesta.getMensaje();
	}

	public RespuestaCreacion(String idGenerado, boolean estadoOperacion) {
		this.idGenerado = idGenerado;
		this.estadoOperacion = estadoOperacion;
		if (estadoOperacion) {
			setMensaje("La operación se pudo realizar con éxito.");
		} else {
			setMensaje("La operación no se pudo realizar. Inténtelo de nuevo.");
		}
	}

}
