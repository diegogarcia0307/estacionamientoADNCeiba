package com.estacionamientoceiba.estacionamientoceiba.infraestructura.adaptador.configuracion;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

@Configuration
@ComponentScan("com.baeldung.jdbc")
public class ConexionDB {
	@Bean
	public DataSource mysqlDataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("com.mysql.jdbc.Driver");
		dataSource.setUrl("jdbc:mysql://localhost:3306/estacionamiento");
		dataSource.setUsername("root");
		dataSource.setPassword("ceiba123");

		return dataSource;
	}
}