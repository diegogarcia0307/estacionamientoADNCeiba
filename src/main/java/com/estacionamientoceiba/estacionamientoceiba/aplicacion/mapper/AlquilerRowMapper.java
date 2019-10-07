package com.estacionamientoceiba.estacionamientoceiba.aplicacion.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.estacionamientoceiba.estacionamientoceiba.dominio.modelo.Alquiler;

public class AlquilerRowMapper implements RowMapper<Alquiler> {

	@Override
	public Alquiler mapRow(ResultSet rs, int rowNum) throws SQLException {
		Alquiler alquiler = new Alquiler();

		alquiler.setFechaIngreso(rs.getDate("fechaIngreso"));
		alquiler.setFechaSalida(rs.getDate("fechaSalida"));
		alquiler.setIdVehiculo(rs.getLong("idVehiculo"));
		alquiler.setPago(rs.getDouble("pago"));
		alquiler.setPuesto(rs.getString("puesto"));

		return alquiler;
	}

}
