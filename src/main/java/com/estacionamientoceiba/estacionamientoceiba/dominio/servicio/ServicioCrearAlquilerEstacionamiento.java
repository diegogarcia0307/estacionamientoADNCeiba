package com.estacionamientoceiba.estacionamientoceiba.dominio.servicio;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.estacionamientoceiba.estacionamientoceiba.aplicacion.comando.manejador.respuestas.RespuestaCreacion;
import com.estacionamientoceiba.estacionamientoceiba.dominio.modelo.Vehiculo;
import com.estacionamientoceiba.estacionamientoceiba.dominio.repositorio.RepositorioAlquiler;

public class ServicioCrearAlquilerEstacionamiento {
	private static final String PERMANENCIA = "El vehiculo ya se encuentra dentro del parqueadero";
	private static final String NO_DISPONIBILIDAD = "No hay disponibilidad para el vehiculo que intenta ingresar";
	private static final int CAPACIDADMOTOS = 10;
	private static final int CAPACIDADCARROS = 20;
	private RepositorioAlquiler repositorioAlquiler;

	private static final Logger LOG = LoggerFactory.getLogger(ServicioCrearAlquilerEstacionamiento.class);

	public ServicioCrearAlquilerEstacionamiento(RepositorioAlquiler repositorioAlquiler) {
		this.repositorioAlquiler = repositorioAlquiler;
	}

	public RespuestaCreacion ejecutar(Vehiculo vehiculo) {
		// verificarDisponibilidad(vehiculo.getTipo());
		// verificarPermanencia(vehiculo);
		// Alquiler alquiler = new Alquiler(obtenerHoraIngreso());
		return this.repositorioAlquiler.crearAlquiler(vehiculo);
	}

	/*
	 * private void verificarPermanencia(Vehiculo vehiculo) { if
	 * (repositorioAlquiler.verificarPermanencia(vehiculo.getPlaca())) throw new
	 * ExcepcionGenerica(PERMANENCIA); }
	 * 
	 * 
	 * private void verificarDisponibilidad(int tipo) { List<Alquiler> plazas = new
	 * ArrayList<>(repositorioAlquiler.listarTodo()); int count = 0;
	 * 
	 * for (Alquiler alquiler : plazas) { if (alquiler.getVehiculo().getTipo() ==
	 * tipo) { count++; } }
	 * 
	 * if ((tipo == 1 && count >= CAPACIDADCARROS) || (tipo == 2 && count >=
	 * CAPACIDADMOTOS)) { throw new ExcepcionGenerica(NO_DISPONIBILIDAD); } }
	 */
	/*
	 * private Date obtenerHoraIngreso() { Calendar calendar =
	 * Calendar.getInstance(); SimpleDateFormat sdf = new
	 * SimpleDateFormat("HH:mm:ss dd-MM-yyyy"); String strDate =
	 * sdf.format(calendar.getTime());
	 * 
	 * SimpleDateFormat sp = new SimpleDateFormat("HH:mm:ss dd-MM-yyyy"); Date date
	 * = new Date(); try { date = sp.parse(strDate); } catch (ParseException e) {
	 * LOG.error(e.getMessage(), e); }
	 * 
	 * return date; }
	 */
}
