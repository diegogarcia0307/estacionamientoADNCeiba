package com.estacionamientoceiba.estacionamientoceiba.infraestructura.adaptador.servicio;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.estacionamientoceiba.estacionamientoceiba.aplicacion.comando.manejador.ManejadorCrearAlquilerEstacionamiento;
import com.estacionamientoceiba.estacionamientoceiba.aplicacion.comando.manejador.ManejadorListarAlquileresEstacionamiento;
import com.estacionamientoceiba.estacionamientoceiba.dominio.servicio.servicioCrearAlquiler;
import com.estacionamientoceiba.estacionamientoceiba.infraestructura.adaptador.repositorio.RepositorioAlquilerEnMemoria;

@Configuration
public class BeanServicio {

	@Bean
	public servicioCrearAlquiler servicioCrearAlquiler() {
		return new servicioCrearAlquiler(new RepositorioAlquilerEnMemoria());
	}

	/*
	 * @Bean public ManejadorCrearAlquilerEstacionamiento manejadorCrearAlquiler() {
	 * return new ManejadorCrearAlquilerEstacionamiento(new
	 * servicioCrearAlquiler(new RepositorioAlquilerEnMemoria())); }
	 * 
	 * @Bean public ManejadorListarAlquileresEstacionamiento
	 * manejadorListarAlquileres() { return new
	 * ManejadorListarAlquileresEstacionamiento(new RepositorioAlquilerEnMemoria());
	 * }
	 */
}
