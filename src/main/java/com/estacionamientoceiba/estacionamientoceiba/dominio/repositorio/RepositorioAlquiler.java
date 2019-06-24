package com.estacionamientoceiba.estacionamientoceiba.dominio.repositorio;

import java.util.Collection;

import com.estacionamientoceiba.estacionamientoceiba.dominio.modelo.Alquiler;

/**
 * @author diego.garcia
 *
 */
public interface RepositorioAlquiler {

	/**
	 * Permite crear un alquiler
	 */
	boolean crear(Alquiler alquiler);

	/**
	 * Permite modificar un alquiler
	 */
	boolean salidaAlquiler(String placa);

	/**
	 * Ayuda a verificar si el vehiculo ya se encuentra dentro del parqueadero
	 */
	boolean verificarPermanencia(String placa);

	/**
	 * Genera un listado de alquiler
	 */
	Collection<Alquiler> listar();
}
