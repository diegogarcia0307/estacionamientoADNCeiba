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
	 * Permite calcular la fecha de salida y el valor a pagar
	 */
	Alquiler salidaAlquiler(String placa);

	/**
	 * Permite eliminar el espacio del parqueadero
	 */
	void eliminarPlaza(String placa);

	/**
	 * Ayuda a verificar si el vehiculo ya se encuentra dentro del parqueadero
	 */
	boolean verificarPermanencia(String placa);

	/**
	 * Genera un listado de alquiler
	 */
	Collection<Alquiler> listar();
}
