package com.estacionamientoceiba.estacionamientoceiba.infraestructura.adaptador.servicio;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.estacionamientoceiba.estacionamientoceiba.dominio.servicio.ServicioCrearAlquiler;
import com.estacionamientoceiba.estacionamientoceiba.dominio.servicio.ServicioListarAlquiler;
import com.estacionamientoceiba.estacionamientoceiba.dominio.servicio.ServicioListarVehiculo;
import com.estacionamientoceiba.estacionamientoceiba.dominio.servicio.ServicioSalidaVehiculo;
import com.estacionamientoceiba.estacionamientoceiba.infraestructura.adaptador.repositorio.RepositorioAlquilerEnMySQL;

@Configuration
public class BeanServicio {

	@Bean
	public ServicioCrearAlquiler servicioCrearAlquiler() {
		return new ServicioCrearAlquiler(new RepositorioAlquilerEnMySQL());
	}

	@Bean
	public ServicioListarAlquiler servicioListarAlquiler() {
		return new ServicioListarAlquiler(new RepositorioAlquilerEnMySQL());
	}

	@Bean
	public ServicioSalidaVehiculo servicioSalidaAlquiler() {
		return new ServicioSalidaVehiculo(new RepositorioAlquilerEnMySQL());
	}

	@Bean
	public ServicioListarVehiculo serviciListarVehiculo() {
		return new ServicioListarVehiculo(new RepositorioAlquilerEnMySQL());
	}

}
