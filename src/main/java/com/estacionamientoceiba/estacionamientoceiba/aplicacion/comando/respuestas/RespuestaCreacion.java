package com.estacionamientoceiba.estacionamientoceiba.aplicacion.comando.respuestas;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RespuestaCreacion {

	private String idGenerado;
	private boolean estadoOperacion;
	private String mensaje;

	private static final String RESPUESTA_NEGATIVA = "La operación no se pudo realizar. Inténtelo de nuevo.";
	private static final String RESPUESTA_POSITIVA = "La operación se pudo realizar con éxito.";

	public RespuestaCreacion(RespuestaCreacion respuesta) {
		idGenerado = respuesta.getIdGenerado();
		estadoOperacion = respuesta.isEstadoOperacion();
		mensaje = respuesta.getMensaje();
	}

	public RespuestaCreacion(String idGenerado, boolean estadoOperacion) {
		this.idGenerado = idGenerado;
		this.estadoOperacion = estadoOperacion;
		if (estadoOperacion) {
			setMensaje(RESPUESTA_POSITIVA);
		} else {
			setMensaje(RESPUESTA_NEGATIVA);
		}
	}

}
