package com.estacionamientoceiba.estacionamientoceiba.dominio.repositorio;

import java.util.Collection;

import com.estacionamientoceiba.estacionamientoceiba.aplicacion.comando.manejador.respuestas.RespuestaCreacion;
import com.estacionamientoceiba.estacionamientoceiba.dominio.modelo.Alquiler;

/**
 * @author diego.garcia
 *
 */
public interface RepositorioAlquiler {

	/**
	 * Permite crear un alquiler
	 */
	RespuestaCreacion crear(Alquiler alquiler);

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

	/**
	 * Permite buscar un alquiler en especifico, dependiendo la placa
	 */
	Alquiler buscarAlquiler(String placa);
}
