package com.estacionamientoceiba.estacionamientoceiba.dominio.excepcion;

public class ExcepcionVehiculoObligatorio extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ExcepcionVehiculoObligatorio(String message) {
		super(message);
	}
}
