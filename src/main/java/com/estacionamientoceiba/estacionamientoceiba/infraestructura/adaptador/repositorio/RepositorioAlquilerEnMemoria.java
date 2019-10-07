package com.estacionamientoceiba.estacionamientoceiba.infraestructura.adaptador.repositorio;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import com.estacionamientoceiba.estacionamientoceiba.aplicacion.comando.manejador.respuestas.RespuestaCreacion;
import com.estacionamientoceiba.estacionamientoceiba.dominio.modelo.Alquiler;
import com.estacionamientoceiba.estacionamientoceiba.dominio.modelo.Vehiculo;
import com.estacionamientoceiba.estacionamientoceiba.dominio.repositorio.RepositorioAlquiler;
import com.estacionamientoceiba.estacionamientoceiba.infraestructura.adaptador.configuracion.ConexionDB;
import com.estacionamientoceiba.estacionamientoceiba.infraestructura.adaptador.excepcion.ExcepcionTecnica;

@Repository
public class RepositorioAlquilerEnMemoria implements RepositorioAlquiler {

	private static final String ERROR_OBTENIENDO_EL_NOMBRE_Y_VALOR_DE_OBJETO = "Error obteniendo el nombre y valor de objeto";

	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	private SimpleJdbcInsert simpleJdbcInsert;

	private static ConcurrentHashMap<String, Alquiler> alquileres;
	private static final Logger LOG = LoggerFactory.getLogger(RepositorioAlquilerEnMemoria.class);

	@Autowired
	public RepositorioAlquilerEnMemoria() {
		this.simpleJdbcInsert = new SimpleJdbcInsert(getDataSource());
		this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(getDataSource());
	}

	private DataSource getDataSource() {
		return new ConexionDB().mysqlDataSource();
	}

	@Override
	public RespuestaCreacion crearAlquiler(Vehiculo vehiculo) {

		long idVehiculo = insertarVehiculo(vehiculo);

		Alquiler alquiler = new Alquiler();
		alquiler.setidVehiculo(idVehiculo);

		long idAlquiler = insertarAlquiler(alquiler);

		return new RespuestaCreacion(String.valueOf(idAlquiler), idAlquiler > 0);
	}

	private long insertarVehiculo(Vehiculo vehiculo) {

		SqlParameterSource parametrosVehiculo = new BeanPropertySqlParameterSource(vehiculo);
		simpleJdbcInsert = new SimpleJdbcInsert(getDataSource());

		simpleJdbcInsert.setTableName("vehiculo");
		simpleJdbcInsert.usingGeneratedKeyColumns("idVehiculo");

		Number newId = this.simpleJdbcInsert.executeAndReturnKey(parametrosVehiculo);
		return newId.longValue();

	}

	private long insertarAlquiler(Alquiler alquiler) {

		SqlParameterSource parametrosAlquiler = new BeanPropertySqlParameterSource(alquiler);
		simpleJdbcInsert = new SimpleJdbcInsert(getDataSource());

		simpleJdbcInsert.setTableName("alquiler");
		simpleJdbcInsert.usingGeneratedKeyColumns("idAlquiler");

		Number newId = this.simpleJdbcInsert.executeAndReturnKey(parametrosAlquiler);
		return newId.longValue();
	}

	@Override
	public Alquiler salidaVehiculo(String placa) {

		Alquiler alquilerSalir = new Alquiler();

		/*
		 * for (Alquiler alquiler : obtenerAlquileres()) {
		 * 
		 * if (placa.equalsIgnoreCase(alquiler.getVehiculo().getPlaca())) {
		 * 
		 * Calendar calendar = Calendar.getInstance(); SimpleDateFormat sdf = new
		 * SimpleDateFormat("HH:mm:ss dd-MM-yyyy"); String strDate =
		 * sdf.format(calendar.getTime());
		 * 
		 * SimpleDateFormat sp = new SimpleDateFormat("HH:mm:ss dd-MM-yyyy"); try { Date
		 * date = sp.parse(strDate);
		 * 
		 * alquiler.setFechaSalida(date);
		 * 
		 * alquilerSalir = alquiler; } catch (ParseException e) {
		 * LOG.error(e.getMessage(), e); }
		 * 
		 * } }
		 */
		return alquilerSalir;
	}

	@Override
	public boolean verificarPermanencia(String placa) {
		// return obtenerAlquileres().stream().anyMatch(fila ->
		// fila.getVehiculo().getPlaca().equalsIgnoreCase(placa));
		return true;
	}

	@Override
	public Collection<Alquiler> listarTodo() {
		return obtenerAlquileres();
	}

	private static Collection<Alquiler> obtenerAlquileres() {
		return alquileres.values();
	}

	@Override
	public Alquiler buscarAlquiler(String placa) {
		/*
		 * for (Alquiler alquiler : obtenerAlquileres()) { if
		 * (placa.equalsIgnoreCase(alquiler.getVehiculo().getPlaca())) return alquiler;
		 * }
		 */
		return new Alquiler();
	}

	@Override
	public void eliminarPlaza(String placa) {

		Alquiler alquiler = buscarAlquiler(placa);

		alquileres.values().remove(alquiler);
	}

	private MapSqlParameterSource crearParametros(Object object) {
		MapSqlParameterSource paramSource = new MapSqlParameterSource();
		Field[] fields = object.getClass().getDeclaredFields();
		for (int i = 0; i < fields.length; i++) {
			try {
				Field field = fields[i];
				if (!Modifier.isStatic(field.getModifiers()) && !Modifier.isFinal(field.getModifiers())) {
					field.setAccessible(true);
					paramSource.addValue(field.getName(), field.get(object));
					field.setAccessible(false);
				}
			} catch (Exception e) {
				throw new ExcepcionTecnica(ERROR_OBTENIENDO_EL_NOMBRE_Y_VALOR_DE_OBJETO, e);
			}
		}
		return paramSource;
	}

	@Override
	public Vehiculo buscarVehiculo(String placa) {
		return null;
	}
}
