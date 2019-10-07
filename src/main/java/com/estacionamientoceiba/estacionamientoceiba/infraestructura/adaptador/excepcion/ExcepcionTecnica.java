package com.estacionamientoceiba.estacionamientoceiba.infraestructura.adaptador.excepcion;

public class ExcepcionTecnica extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ExcepcionTecnica(String message, Exception e) {
		super(message, e);
	}

}
