package com.estacionamientoceiba.estacionamientoceiba.infraestructura.adaptador.servicio;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.estacionamientoceiba.estacionamientoceiba.dominio.servicio.ServicioCrearAlquilerEstacionamiento;
import com.estacionamientoceiba.estacionamientoceiba.dominio.servicio.ServicioListarAlquilerEstacionamiento;
import com.estacionamientoceiba.estacionamientoceiba.dominio.servicio.ServicioSalidaVehiculo;
import com.estacionamientoceiba.estacionamientoceiba.infraestructura.adaptador.repositorio.RepositorioAlquilerEnMemoria;

@Configuration
public class BeanServicio {

	@Bean
	public ServicioCrearAlquilerEstacionamiento servicioCrearAlquiler() {
		return new ServicioCrearAlquilerEstacionamiento(new RepositorioAlquilerEnMemoria());
	}

	@Bean
	public ServicioListarAlquilerEstacionamiento servicioListarAlquiler() {
		return new ServicioListarAlquilerEstacionamiento(new RepositorioAlquilerEnMemoria());
	}

	@Bean
	public ServicioSalidaVehiculo servicioSalidaAlquiler() {
		return new ServicioSalidaVehiculo(new RepositorioAlquilerEnMemoria());
	}

}
