package com.estacionamientoceiba.estacionamientoceiba.infraestructura.adaptador.repositorio;

import java.util.Collection;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import com.estacionamientoceiba.estacionamientoceiba.aplicacion.mapper.AlquilerRowMapper;
import com.estacionamientoceiba.estacionamientoceiba.aplicacion.mapper.VehiculoRowMapper;
import com.estacionamientoceiba.estacionamientoceiba.dominio.modelo.Alquiler;
import com.estacionamientoceiba.estacionamientoceiba.dominio.modelo.Vehiculo;
import com.estacionamientoceiba.estacionamientoceiba.dominio.repositorio.RepositorioAlquiler;
import com.estacionamientoceiba.estacionamientoceiba.infraestructura.adaptador.configuracion.ConexionDB;

@Repository
public class RepositorioAlquilerEnMemoria implements RepositorioAlquiler {

	private static final String QUERY_BUSCAR_VEHICULO = "SELECT * FROM vehiculo WHERE placa LIKE ?";
	private static final String QUERY_BUSCAR_ALQUILER = "SELECT a.* FROM alquiler as a, vehiculo as v WHERE a.idVehiculo = v.idVehiculo and v.placa like ?";
	private static final String QUERY_DISPONIBILIDAD_CARROS = "SELECT COUNT(a.idAlquiler) as conteo FROM alquiler as a, vehiculo as v WHERE a.fechaSalida IS null and a.idVehiculo = v.idVehiculo and v.tipo = 1";
	private static final String QUERY_DISPONIBILIDAD_MOTOS = "SELECT COUNT(a.idAlquiler) as conteo FROM alquiler as a, vehiculo as v WHERE a.fechaSalida IS null and a.idVehiculo = v.idVehiculo and v.tipo = 2";
	private static final String QUERY_PERMANENCIA_VEHICULO = "SELECT v.* FROM alquiler as a , vehiculo as v WHERE a.fechaSalida IS null and a.idVehiculo = v.idVehiculo and v.placa like ?";
	private static final String QUERY_SALIDA_VEHICULO = "UPDATE alquiler SET fechaSalida = :fechaSalida, pago = :pago WHERE idAlquiler = :idAlquiler";

	private static final int CAPACIDAD_CARROS = 20;
	private static final int CAPACIDAD_MOTOS = 10;

	private SimpleJdbcInsert simpleJdbcInsert;
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	@Autowired
	public RepositorioAlquilerEnMemoria() {
		this.simpleJdbcInsert = new SimpleJdbcInsert(getDataSource());
		this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(getDataSource());
	}

	private DataSource getDataSource() {
		return new ConexionDB().mysqlDataSource();
	}

	@Override
	public long crearAlquiler(Vehiculo vehiculo) {

		long idVehiculo = insertarVehiculo(vehiculo);

		Alquiler alquiler = new Alquiler();
		alquiler.setIdVehiculo(idVehiculo);

		return insertarAlquiler(alquiler);

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
	public double salidaVehiculo(Alquiler alquiler) {

		SqlParameterSource parametros = new BeanPropertySqlParameterSource(alquiler);

		namedParameterJdbcTemplate.update(QUERY_SALIDA_VEHICULO, parametros);

		return alquiler.getPago();
	}

	@Override
	public boolean comprobarPermanenciaVehiculo(String placa) {

		try {
			simpleJdbcInsert.getJdbcTemplate().queryForObject(QUERY_PERMANENCIA_VEHICULO, new Object[] { placa },
					new VehiculoRowMapper());
		} catch (EmptyResultDataAccessException emptyR) {
			return false;
		}

		return true;
	}

	@Override
	public Collection<Alquiler> listarTodoAlquiler() {
		return null;
	}

	@Override
	public Alquiler buscarAlquiler(String placa) {

		return simpleJdbcInsert.getJdbcTemplate().queryForObject(QUERY_BUSCAR_ALQUILER, new Object[] { placa },
				new AlquilerRowMapper());

	}

	@Override
	public Vehiculo buscarVehiculo(String placa) {

		return simpleJdbcInsert.getJdbcTemplate().queryForObject(QUERY_BUSCAR_VEHICULO, new Object[] { placa },
				new VehiculoRowMapper());

	}

	@Override
	public boolean verificarDisponibilidad(int tipo) {

		int cantidadActual = 0;
		boolean estado = true;

		if (tipo == 1) {
			cantidadActual = simpleJdbcInsert.getJdbcTemplate().queryForObject(QUERY_DISPONIBILIDAD_CARROS,
					Integer.class);
			estado = cantidadActual < CAPACIDAD_CARROS;
		} else if (tipo == 2) {

			cantidadActual = simpleJdbcInsert.getJdbcTemplate().queryForObject(QUERY_DISPONIBILIDAD_MOTOS,
					Integer.class);
			estado = cantidadActual < CAPACIDAD_MOTOS;
		}

		return estado;

	}

	@Override
	public Collection<Vehiculo> listarTodoVehiculo() {
		return null;
	}
}
