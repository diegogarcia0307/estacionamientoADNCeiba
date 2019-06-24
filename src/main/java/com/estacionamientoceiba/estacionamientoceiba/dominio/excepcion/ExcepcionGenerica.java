package com.estacionamientoceiba.estacionamientoceiba.dominio.excepcion;

public class ExcepcionGenerica extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ExcepcionGenerica(String mensaje) {
		super(mensaje);
	}
}
