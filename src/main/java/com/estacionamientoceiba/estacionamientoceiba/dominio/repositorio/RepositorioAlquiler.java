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
	void crear(Alquiler alquiler);

	/**
	 * Permite modificar un alquiler
	 */
	boolean modificar(String placa);

	/**
	 * Ayuda a verificar si el vehiculo ya se encuentra dentro del parqueadero
	 */
	boolean verificarPermanencia(Alquiler alquiler);

	/**
	 * Genera un listado de alquiler
	 */
	Collection<Alquiler> listar();
}
