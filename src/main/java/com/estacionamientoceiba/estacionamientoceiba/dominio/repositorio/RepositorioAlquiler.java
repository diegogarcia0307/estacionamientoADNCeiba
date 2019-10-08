package com.estacionamientoceiba.estacionamientoceiba.dominio.repositorio;

import java.util.Collection;

import com.estacionamientoceiba.estacionamientoceiba.dominio.modelo.Alquiler;
import com.estacionamientoceiba.estacionamientoceiba.dominio.modelo.Vehiculo;

/**
 * @author diego.garcia
 *
 */
public interface RepositorioAlquiler {

	/**
	 * Permite crear un alquiler y retorna el nuevo id
	 */
	long crearAlquiler(Vehiculo vehiculo);

	/**
	 * Permite calcular la fecha de salida de un vehiculo, y retorna el valor a
	 * pagar
	 */
	double salidaVehiculo(Alquiler alquiler);

	/**
	 * Ayuda a verificar si el vehiculo ya se encuentra dentro del parqueadero
	 */
	boolean comprobarPermanenciaVehiculo(String placa);

	boolean verificarDisponibilidad(int tipo);

	/**
	 * Genera un listado de alquiler
	 */
	Collection<Alquiler> listarTodoAlquiler();

	Collection<Vehiculo> listarTodoVehiculo();

	/**
	 * Permite buscar un alquiler en especifico, dependiendo la placa
	 */
	Alquiler buscarAlquiler(String placa);

	Vehiculo buscarVehiculo(String placa);
}
