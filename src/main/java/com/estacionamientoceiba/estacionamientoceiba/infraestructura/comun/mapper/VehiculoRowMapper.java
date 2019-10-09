package com.estacionamientoceiba.estacionamientoceiba.infraestructura.comun.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.estacionamientoceiba.estacionamientoceiba.dominio.modelo.Vehiculo;

public class VehiculoRowMapper implements RowMapper<Vehiculo> {

	@Override
	public Vehiculo mapRow(ResultSet rs, int rowNum) throws SQLException {

		Vehiculo vehiculo = new Vehiculo();

		vehiculo.setTipo(rs.getInt("tipo"));
		vehiculo.setPlaca(rs.getString("placa"));
		vehiculo.setMarca(rs.getString("marca"));
		vehiculo.setCilindraje(rs.getDouble("cilindraje"));

		return vehiculo;
	}

}
